package com.thinkgem.jeesite.modules.sh.apply.entity;
import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 报名表Entity
 * @author huangyunquan
 * @version 2017-04-09
 */
public class ShApply extends DataEntity<ShApply> {
	
	private static final long serialVersionUID = 1L;
//	private String applyInfoId;		// 报名信息id
	/**报名信息id*/
	private ShApplyInfo info;
	private String applyNo;		// 报名号
	private String userId;		// 考生id
	private String applyStatus;		// 报名状态（0待审核 1已通过 2未通过）
	
	public static String STANDBY_ENSURE = "0";//"待审核";
	public static String PASS = "1";// "已通过";
	public static String FAIL = "2";//"未通过";
	
	public ShApply() {
		super();
	}

	public ShApply(String id){
		super(id);
	}

	
	public ShApplyInfo getInfo() {
		return info;
	}

	public void setInfo(ShApplyInfo info) {
		this.info = info;
	}

	@Length(min=0, max=20, message="报名号长度必须介于 0 和 20 之间")
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min=0, max=1, message="报名状态（0待审核 1已通过 2未通过）长度必须介于 0 和 1 之间")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
}