package com.lyq.work.entity.demand;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 需求单
 * @author onlineGenerator
 * @date 2017-07-28 08:33:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "work_demand", schema = "")
@SuppressWarnings("serial")
public class WorkDemandEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	@Excel(name="流程状态")
	private java.lang.String bpmStatus;
	/**标题*/
	@Excel(name="标题")
	private java.lang.String title;
	/**需求内容*/
	@Excel(name="需求内容")
	private java.lang.String content;
	/**提出人*/
	@Excel(name="提出人")
	private java.lang.String proposeMan;
	/**提出日期*/
	@Excel(name="提出日期",format = "yyyy-MM-dd")
	private java.util.Date proposeDate;
	/**工程师*/
	@Excel(name="工程师")
	private java.lang.String programmer;
	/**完成时间*/
	@Excel(name="完成时间",format = "yyyy-MM-dd")
	private java.util.Date finishTime;
	/**完成备注*/
	@Excel(name="完成备注")
	private java.lang.String finishRemark;
	/**附件*/
	@Excel(name="附件")
	private java.lang.String attachment;
	/**级别*/
	@Excel(name="级别")
	private java.lang.String level;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标题
	 */
	@Column(name ="TITLE",nullable=false,length=100)
	public java.lang.String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标题
	 */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  需求内容
	 */
	@Column(name ="CONTENT",nullable=true,length=500)
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  需求内容
	 */
	public void setContent(java.lang.String content){
		this.content = content;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提出人
	 */
	@Column(name ="PROPOSE_MAN",nullable=true,length=32)
	public java.lang.String getProposeMan(){
		return this.proposeMan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提出人
	 */
	public void setProposeMan(java.lang.String proposeMan){
		this.proposeMan = proposeMan;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  提出日期
	 */
	@Column(name ="PROPOSE_DATE",nullable=true,length=32)
	public java.util.Date getProposeDate(){
		return this.proposeDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  提出日期
	 */
	public void setProposeDate(java.util.Date proposeDate){
		this.proposeDate = proposeDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工程师
	 */
	@Column(name ="PROGRAMMER",nullable=true,length=32)
	public java.lang.String getProgrammer(){
		return this.programmer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工程师
	 */
	public void setProgrammer(java.lang.String programmer){
		this.programmer = programmer;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  完成时间
	 */
	@Column(name ="FINISH_TIME",nullable=true,length=32)
	public java.util.Date getFinishTime(){
		return this.finishTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  完成时间
	 */
	public void setFinishTime(java.util.Date finishTime){
		this.finishTime = finishTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  完成备注
	 */
	@Column(name ="FINISH_REMARK",nullable=true,length=200)
	public java.lang.String getFinishRemark(){
		return this.finishRemark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  完成备注
	 */
	public void setFinishRemark(java.lang.String finishRemark){
		this.finishRemark = finishRemark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  附件
	 */
	@Column(name ="ATTACHMENT",nullable=true,length=200)
	public java.lang.String getAttachment(){
		return this.attachment;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  附件
	 */
	public void setAttachment(java.lang.String attachment){
		this.attachment = attachment;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  级别
	 */
	@Column(name ="LEVEL",nullable=true,length=32)
	public java.lang.String getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  级别
	 */
	public void setLevel(java.lang.String level){
		this.level = level;
	}
}
