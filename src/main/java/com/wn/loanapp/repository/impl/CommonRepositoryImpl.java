package com.wn.loanapp.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

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
			    + " join api_request c on c.order_no=b.odr_no and c.distributor_id=b.distr_id where b.loan_flag='t' "
			    + " and b.loan_accept_flg='f' and b.loan_decline_flg='f' " );
		
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getDistributer())) {
			hql.append(" and c.distributor_id in " + loanDetailsForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateStart())) {
			hql.append(" and a.tn_date>='" + loanDetailsForm.getTnDateStart() + "'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateEnd())) {
			hql.append(" and a.tn_date<='" + loanDetailsForm.getTnDateEnd()+ "'");
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
		/*StringBuilder hql =  new StringBuilder("select count(*) from wn_purchase_tbl a "
			    + " join wn_order b on b.odr_no=a.txn_id "
			    + " join api_request c on c.order_no=b.odr_no "
			    + " and c.distributor_id=b.distr_id where b.loan_flag='t' "
			    + " and b.loan_accept_flg='f' and b.loan_decline_flg='f' " );
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateStart())) {
			hql.append(" and a.tn_date>='" + loanDetailsForm.getTnDateStart() + "'");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDetailsForm.getTnDateEnd())) {
			hql.append(" and a.tn_date<='" + loanDetailsForm.getTnDateEnd()+ "'");
		}
		Query query = getPrimaryEntityManager().createQuery(hql.toString());
		long  loanCount = (Long)query.getSingleResult();*/
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
				+" WHERE c.block_status = '1' AND c.tranche_status = '1' AND c.b_settle_stat = 'f'"
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
				+" AND c.b_settle_stat = 'f' AND pay_mode = 'CFS_LOAN'");
		
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getDistributer())) {
			sql.append(" and a.distributor_id in " + loanDispersedForm.getDistributer());
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnStartDate())){
			sql.append(" AND Date(c.tn_date) >= '"+loanDispersedForm.getTnStartDate()+" '");
		}
		if(Format.isStringNotEmptyAndNotNull(loanDispersedForm.getTnEndDate())){
			sql.append(" AND Date(c.tn_date) <= '"+loanDispersedForm.getTnEndDate()+"'");
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
}
