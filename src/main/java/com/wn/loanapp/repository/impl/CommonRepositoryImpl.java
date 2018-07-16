package com.wn.loanapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.wn.loanapp.dto.BankStatementDTO;
import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.dto.LoanDispersedDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.form.LoanDispersedForm;
import com.wn.loanapp.model.CommonEntity;
import com.wn.loanapp.repository.CommonRepository;
import com.wn.loanapp.util.Format;

@Repository(CommonRepositoryImpl.REPOSITORY_NAME)
public class CommonRepositoryImpl extends PrimaryGenericRepositoryImpl<CommonEntity, String> implements CommonRepository{

	public static final String REPOSITORY_NAME = "commonRepository";
	
	public CommonRepositoryImpl(){
		super(CommonEntity.class);
	}

	/* (non-Javadoc)
	 * @see com.wn.loanapp.repository.CommonRepository#getLoanDetails(com.wn.loanapp.form.LoanDetailsForm)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<LoanDetailsDTO> getAppliedLoanDetails(LoanDetailsForm loanDetailsForm) {
		StringBuilder hql =  new StringBuilder("select distinct b.odr_no as orderNo, c.distributor_name as distributorName, a.first_name as firstName,"
			    + " a.tn_date as tnDate, a.amount as amount from wn_purchase_tbl a "
			    + " join wn_order b on b.odr_no=a.txn_id "
			    + " join api_request c on c.order_no=b.odr_no and c.distributor_id=b.distr_id where b.loan_flag='t' " );
			/*    + " and b.loan_accept_flg='f' and b.loan_decline_flg='f' " );*/
		
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getDistributer())) {
			hql.append(" and c.distributor_id in " + loanDetailsForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateStart())) {
			hql.append(" and a.tn_date>='" + loanDetailsForm.getTnDateStart() + "'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateEnd())) {
			hql.append(" and a.tn_date<='" + loanDetailsForm.getTnDateEnd()+ "'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getLoanApplied())) {
			hql.append(" and b.loan_accept_flg = '" + loanDetailsForm.getLoanApplied()+ "'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getLoanPending())) {
			hql.append(" and b.loan_decline_flg = '" + loanDetailsForm.getLoanPending()+ "'");
		}
		
		hql.append(" order by tnDate desc");
		if(Format.isIntegerNotEmtyAndNotZero(loanDetailsForm.getLength())) {
			hql.append(" limit " + loanDetailsForm.getLength());
		}
		if(Format.isNotNull(loanDetailsForm.getStart())) {
			hql.append(" offset " + loanDetailsForm.getStart());
		}
		List<LoanDetailsDTO> dtos = getSession()
                .createSQLQuery(hql.toString())
                .addScalar("orderNo", StandardBasicTypes.STRING)
                .addScalar("distributorName", StandardBasicTypes.STRING)
                .addScalar("firstName", StandardBasicTypes.STRING)
                .addScalar("tnDate", StandardBasicTypes.STRING)
                .addScalar("amount", StandardBasicTypes.LONG)
                .setResultTransformer(new AliasToBeanResultTransformer(LoanDetailsDTO.class))
                .list();
        return dtos;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<Object> getDistributers() {
		StringBuilder hql =  new StringBuilder("select distinct c.distr_id as distId, p_descr as distName"
			    + " from wn_distr_hdr c where c.distr_id ilike 'T%'");
		List<Object> dtos = getSession()
                .createSQLQuery(hql.toString())
                .addScalar("distId", StandardBasicTypes.STRING)
                .addScalar("distName", StandardBasicTypes.STRING)
                .list();
        return dtos;
	}

	@Override
	public Long getAppliedLoanDetailsCount(LoanDetailsForm loanDetailsForm) {
		StringBuilder sql =  new StringBuilder("select count(distinct b.odr_no) from wn_purchase_tbl a "
			    + " join wn_order b on b.odr_no=a.txn_id "
			    + " join api_request c on c.order_no=b.odr_no "
			    + " and c.distributor_id=b.distr_id where b.loan_flag='t' "
			    + " and b.loan_accept_flg='f' and b.loan_decline_flg='f' " );
		
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getDistributer())) {
			sql.append(" and c.distributor_id in " + loanDetailsForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateStart())) {
			sql.append(" and a.tn_date>='" + loanDetailsForm.getTnDateStart() + "'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateEnd())) {
			sql.append(" and a.tn_date<='" + loanDetailsForm.getTnDateEnd()+ "'");
		}
		
		Session hibernateSession = (Session) getPrimaryEntityManager().unwrap(Session.class);
		Connection con = ((SessionImpl) hibernateSession).connection();
        Long loanCount = (long) 0;
        
        try {
            try {
                Statement st = con.createStatement();
                ResultSet res = st.executeQuery(sql.toString());
                while (res.next()){
                	//loanCount = (long) res.getInt(1);
                	loanCount = res.getLong(1);
                }
                System.out.println("Number of row:"+loanCount);
            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return loanCount;
	}
	
	private Session getSession() {
        return (Session) getPrimaryEntityManager().unwrap(Session.class);
    }

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<LoanDispersedDTO> getDispersedLoanDetails(LoanDispersedForm loanDispersedForm) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT c.txn_id as txnId, c.online_payment_id as onlinePaymentId,"
				+" a.retailer_name as retailerName,"
				+" c.amount as amount, c.tn_date AS tnDate," 
				+" c.repay_txn_id as repayTxnId, c.settle_amt as settleAmt, c.c_verify as verify" 
				+" FROM api_request AS a INNER JOIN wn_purchase_tbl c ON a.order_no = c.txn_id "
				+" WHERE c.block_status = '1' AND c.tranche_status = '1' "
				+" AND pay_mode = 'CFS_LOAN'");
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getDistributer())) {
			sql.append(" and a.distributor_id in " + loanDispersedForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnStartDate())){
			sql.append(" AND Date(c.tn_date) >= '"+loanDispersedForm.getTnStartDate()+" '");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnEndDate())){
			sql.append(" AND Date(c.tn_date) <= '"+loanDispersedForm.getTnEndDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getSettleState())){
			sql.append(" AND c.b_settle_stat = '"+loanDispersedForm.getSettleState()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getIsVerify())){
			sql.append(" AND c.c_verify = '"+loanDispersedForm.getIsVerify()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getOnlinePaymentId())){
			sql.append(" AND c.online_payment_id in "+loanDispersedForm.getOnlinePaymentId());
		}
		sql.append(" ORDER BY c.tn_date DESC, c.online_payment_id DESC");
		if(Format.isIntegerNotEmtyAndNotZero(loanDispersedForm.getLength())) {
			sql.append(" limit " + loanDispersedForm.getLength());
		}
		if(Format.isNotNull(loanDispersedForm.getStart())) {
			sql.append(" offset " + loanDispersedForm.getStart());
		}
		List<LoanDispersedDTO> dtos = getSession()
                .createSQLQuery(sql.toString())
                .addScalar("txnId", StandardBasicTypes.STRING)
                .addScalar("onlinePaymentId", StandardBasicTypes.STRING)
                .addScalar("retailerName", StandardBasicTypes.STRING)
                .addScalar("amount", StandardBasicTypes.STRING)
                .addScalar("tnDate", StandardBasicTypes.STRING)
                .addScalar("repayTxnId", StandardBasicTypes.STRING)
                .addScalar("settleAmt", StandardBasicTypes.STRING)
                .addScalar("verify", StandardBasicTypes.STRING)
                .setResultTransformer(new AliasToBeanResultTransformer(LoanDispersedDTO.class))
                .list();
		
        return dtos;
	}

	@Override
	public Long getDispersedLoanDetailsCount(LoanDispersedForm loanDispersedForm) {
		StringBuilder sql = new StringBuilder("SELECT count (DISTINCT c.txn_id) FROM api_request AS a INNER JOIN "
				+" wn_purchase_tbl c ON a.order_no = c.txn_id  WHERE c.block_status = '1' AND c.tranche_status = '1'"
				+" AND pay_mode = 'CFS_LOAN'");
		
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getDistributer())) {
			sql.append(" and a.distributor_id in " + loanDispersedForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnStartDate())){
			sql.append(" AND Date(c.tn_date) >= '"+loanDispersedForm.getTnStartDate()+" '");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnEndDate())){
			sql.append(" AND Date(c.tn_date) <= '"+loanDispersedForm.getTnEndDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getSettleState())){
			sql.append(" AND c.b_settle_stat = '"+loanDispersedForm.getSettleState()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getIsVerify())){
			sql.append(" AND c.c_verify = '"+loanDispersedForm.getIsVerify()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getOnlinePaymentId())){
			sql.append(" AND c.online_payment_id in "+loanDispersedForm.getOnlinePaymentId());
		}
		Session hibernateSession = (Session) getPrimaryEntityManager().unwrap(Session.class);
		Connection con = ((SessionImpl) hibernateSession).connection();
        Long loanCount = (long) 0;
        
        try {
            try {
                Statement st = con.createStatement();
                ResultSet res = st.executeQuery(sql.toString());
                while (res.next()){
                	//loanCount = (long) res.getInt(1);
                	loanCount = res.getLong(1);
                }
                System.out.println("Number of row:"+loanCount);
            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return loanCount;
	}

	@Override
	public void updateBankStatement(BankStatementDTO bankStatementDTO) throws SQLException{
		Session hibernateSession = (Session) getPrimaryEntityManager().unwrap(Session.class);
		Connection con = ((SessionImpl) hibernateSession).connection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE wn_purchase_tbl set settle_dte = ?, settle_amt = ?, b_settle_stat = ? where online_payment_id = ? ";
		preparedStatement = con.prepareStatement(sql);
		
		preparedStatement.setTimestamp(1, Format.convertStringDateToSqlTimestamp(bankStatementDTO.getSettleDate()));
		if(Format.isNotNull(bankStatementDTO.getSettleAmount())){
			Double amount = Double.parseDouble(bankStatementDTO.getSettleAmount());
			preparedStatement.setInt(2, amount.intValue());
		}else{
			preparedStatement.setInt(2, 0);
		}
		preparedStatement.setBoolean(3, true);
		if(Format.isStringNotEmptyAndNotNull(bankStatementDTO.getOnlinePaymentId())){
			preparedStatement.setString(4, bankStatementDTO.getOnlinePaymentId());
			preparedStatement.executeUpdate();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<LoanDispersedDTO> getInvoicesLoanDetails(LoanDispersedForm loanDispersedForm) {
		StringBuilder sql = new StringBuilder("SELECT DISTINCT c.txn_id as txnId, c.online_payment_id as onlinePaymentId,"
				+" a.retailer_name as retailerName,"
				+" c.amount as amount, c.tn_date AS tnDate," 
				+" c.repay_txn_id as repayTxnId, c.settle_amt as settleAmt, c.c_verify as verify, c.repay_dte as repayDte" 
				+" FROM api_request AS a INNER JOIN wn_purchase_tbl c ON a.order_no = c.txn_id "
				+" WHERE c.block_status = '1' AND c.tranche_status = '1' "
				+" AND c.settle_status = 't' AND c.b_verify_flag = 't' AND pay_mode = 'CFS_LOAN'");
		
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getDistributer())){
			sql.append(" and a.distributor_id in " + loanDispersedForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnStartDate())){
			sql.append(" AND Date(c.tn_date) >= '"+loanDispersedForm.getTnStartDate()+" '");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnEndDate())){
			sql.append(" AND Date(c.tn_date) <= '"+loanDispersedForm.getTnEndDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getRepayStartDate())){
			sql.append(" AND Date(c.repay_dte) >= '"+loanDispersedForm.getRepayStartDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getRepayEndDate())){
			sql.append(" AND Date(c.repay_dte) <= '"+loanDispersedForm.getRepayEndDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getSettleState())){
			sql.append("  AND c.b_settle_stat = '"+loanDispersedForm.getSettleState()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getOnlinePaymentId())){
			sql.append(" AND c.online_payment_id in "+loanDispersedForm.getOnlinePaymentId());
		}
		sql.append(" ORDER BY c.tn_date DESC, c.online_payment_id DESC");
		if(Format.isIntegerNotEmtyAndNotZero(loanDispersedForm.getLength())) {
			sql.append(" limit " + loanDispersedForm.getLength());
		}
		if(Format.isNotNull(loanDispersedForm.getStart())) {
			sql.append(" offset " + loanDispersedForm.getStart());
		}
		List<LoanDispersedDTO> dtos = getSession()
                .createSQLQuery(sql.toString())
                .addScalar("txnId", StandardBasicTypes.STRING)
                .addScalar("onlinePaymentId", StandardBasicTypes.STRING)
                .addScalar("retailerName", StandardBasicTypes.STRING)
                .addScalar("amount", StandardBasicTypes.STRING)
                .addScalar("tnDate", StandardBasicTypes.STRING)
                .addScalar("repayDte", StandardBasicTypes.STRING)
                .addScalar("repayTxnId", StandardBasicTypes.STRING)
                .addScalar("settleAmt", StandardBasicTypes.STRING)
                .addScalar("verify", StandardBasicTypes.STRING)
                .setResultTransformer(new AliasToBeanResultTransformer(LoanDispersedDTO.class))
                .list();
		
        return dtos;
		
	}
	
	@Override
	public Long getInvoicesLoanDetailsCount(LoanDispersedForm loanDispersedForm) {
		StringBuilder sql = new StringBuilder("SELECT count (DISTINCT c.txn_id) FROM api_request AS a INNER JOIN "
				+" wn_purchase_tbl c ON a.order_no = c.txn_id  WHERE c.block_status = '1' AND c.tranche_status = '1'"
				+" AND c.b_settle_stat = 't' AND c.settle_status = 't' AND c.b_verify_flag = 't' AND pay_mode = 'CFS_LOAN' ");
		
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getDistributer())) {
			sql.append(" and a.distributor_id in " + loanDispersedForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnStartDate())){
			sql.append(" AND Date(c.tn_date) >= '"+loanDispersedForm.getTnStartDate()+" '");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnEndDate())){
			sql.append(" AND Date(c.tn_date) <= '"+loanDispersedForm.getTnEndDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getRepayStartDate())){
			sql.append(" AND Date(c.repay_dte) >= '"+loanDispersedForm.getRepayStartDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getRepayEndDate())){
			sql.append(" AND Date(c.repay_dte) <= '"+loanDispersedForm.getRepayEndDate()+"'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getOnlinePaymentId())){
			sql.append(" AND c.online_payment_id in "+loanDispersedForm.getOnlinePaymentId());
		}
		Session hibernateSession = (Session) getPrimaryEntityManager().unwrap(Session.class);
		Connection con = ((SessionImpl) hibernateSession).connection();
        Long loanCount = (long) 0;
        
        try {
            try {
                Statement st = con.createStatement();
                ResultSet res = st.executeQuery(sql.toString());
                while (res.next()){
                	//loanCount = (long) res.getInt(1);
                	loanCount = res.getLong(1);
                }
                System.out.println("Number of row:"+loanCount);
            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return loanCount;
	}
	@Override
	public void updatePendingLoans(BankStatementDTO bankStatementDTO) throws SQLException {
		Session hibernateSession = (Session) getPrimaryEntityManager().unwrap(Session.class);
		Connection con = ((SessionImpl) hibernateSession).connection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE wn_purchase_tbl set repay_dte = ?, repay_amt = ? where online_payment_id = ? ";
		preparedStatement = con.prepareStatement(sql);
		
		preparedStatement.setTimestamp(1, Format.convertStringDateToSqlTimestamp(bankStatementDTO.getRepayDate()));
		if(Format.isNotNull(bankStatementDTO.getRepayAmount())){
			Double amount = Double.parseDouble(bankStatementDTO.getRepayAmount());
			preparedStatement.setInt(2, amount.intValue());
		}else{
			preparedStatement.setInt(2, 0);
		}
		
		if(Format.isStringNotEmptyAndNotNull(bankStatementDTO.getOnlinePaymentId())){
			preparedStatement.setString(3, bankStatementDTO.getOnlinePaymentId());
			preparedStatement.executeUpdate();
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Object> getRetailers(){
		StringBuilder sql = new StringBuilder(" select distinct c.customer_id as retId, c.first_name as retName"
			    + " from wn_purchase_tbl c where c.sub_type= 'CFS_LOAN' group by customer_id, usr_id, first_name ");
		
		List<Object> dtos = getSession()
				.createSQLQuery(sql.toString())
				.addScalar("retId", StandardBasicTypes.STRING)
				.addScalar("retName", StandardBasicTypes.STRING)
				.list();
		
		return dtos;
	}

	@Override
	public void updateDistributorInvoice(BankStatementDTO bankStatementDTO) throws SQLException {
		Session hibernateSession = (Session) getPrimaryEntityManager().unwrap(Session.class);
		Connection con = ((SessionImpl) hibernateSession).connection();
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE wn_purchase_tbl set settle_dte = ?, settle_amt = ? where online_payment_id = ? ";
		preparedStatement = con.prepareStatement(sql);
		
		preparedStatement.setTimestamp(1, Format.convertStringDateToSqlTimestamp(bankStatementDTO.getSettleDate()));
		if(Format.isNotNull(bankStatementDTO.getSettleAmount())){
			Double amount = Double.parseDouble(bankStatementDTO.getSettleAmount());
			preparedStatement.setInt(2, amount.intValue());
		}else{
			preparedStatement.setInt(2, 0);
		}
		if(Format.isStringNotEmptyAndNotNull(bankStatementDTO.getOnlinePaymentId())){
			preparedStatement.setString(3, bankStatementDTO.getOnlinePaymentId());
			preparedStatement.executeUpdate();
		}
	}
	
}
