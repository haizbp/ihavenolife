package hm.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "action")
public class ActionEntity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actionClass;
	private String requiredParams;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private PageEntity page;

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getRequiredParams() {
		return requiredParams;
	}

	public void setRequiredParams(String requiredParams) {
		this.requiredParams = requiredParams;
	}

	public PageEntity getPage() {
		return page;
	}

	public void setPage(PageEntity page) {
		this.page = page;
	}

}
