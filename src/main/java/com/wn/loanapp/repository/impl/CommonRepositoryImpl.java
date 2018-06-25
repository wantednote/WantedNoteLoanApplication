package com.wn.loanapp.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.wn.loanapp.dto.LoanDetailsDTO;
import com.wn.loanapp.form.LoanDetailsForm;
import com.wn.loanapp.model.CommonEntity;
import com.wn.loanapp.repository.CommonRepository;

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
	public List<LoanDetailsDTO> getLoanDetails(LoanDetailsForm loanDetailsForm) {
		StringBuilder hql =  new StringBuilder(
			    "select distinct b.odr_no as orderNo, c.distributor_name as distributorName, a.first_name as firstName,"
			    + " a.tn_date as tnDate, a.amount as amount from wn_purchase_tbl a "
			    + " join wn_order b on b.odr_no=a.txn_id "
			    + " join api_request c on c.order_no=b.odr_no and c.distributor_id=b.distr_id where b.loan_flag='t' "
			    + " and b.loan_accept_flg='f' and b.loan_decline_flg='f' " 
			    + " and a.tn_date>='" + loanDetailsForm.getTnDateStart() + "' "
			    + " and a.tn_date<='" + loanDetailsForm.getTnDateEnd()+ "' order by tnDate desc "
			);
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
	
	private Session getSession() {
        return (Session) getPrimaryEntityManager().unwrap(Session.class);
    }
}
