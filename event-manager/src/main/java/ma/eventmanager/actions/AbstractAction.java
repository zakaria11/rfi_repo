package ma.eventmanager.actions;

import com.opensymphony.xwork2.ActionSupport;

public class AbstractAction extends ActionSupport{

	protected Integer records;
	protected Integer rows;
	protected int total;
	protected Integer page = 0;
	protected String oper;
	protected String _search;
	protected String grouped;
	protected String groupBy;
	
	public Integer getRecords()
	{
		return records;
	}
	public void setRecords(Integer records)
	{
		this.records = records;
	}
	public Integer getRows()
	{
		return rows;
	}
	public void setRows(Integer rows)
	{
		this.rows = rows;
	}
	public int getTotal()
	{
		return total;
	}
	public void setTotal(int total)
	{
		this.total = total;
	}
	public Integer getPage()
	{
		return page;
	}
	public void setPage(Integer page)
	{
		this.page = page;
	}
	public String getOper()
	{
		return oper;
	}
	public void setOper(String oper)
	{
		this.oper = oper;
	}
	public String get_search()
	{
		return _search;
	}
	public void set_search(String _search)
	{
		this._search = _search;
	}
	public String getGrouped()
	{
		return grouped;
	}
	public void setGrouped(String grouped)
	{
		this.grouped = grouped;
	}
	public String getGroupBy()
	{
		return groupBy;
	}
	public void setGroupBy(String groupBy)
	{
		this.groupBy = groupBy;
	}

	
	
}
