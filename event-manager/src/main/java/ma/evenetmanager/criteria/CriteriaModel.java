package ma.evenetmanager.criteria;

public class CriteriaModel{

	private String fieldName;
	private String fieldValue;
	private String type; 
	
	
	
	public CriteriaModel(){}

	
	public CriteriaModel(String fieldName, String fieldValue){
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


	public String getFieldName()
	{
		return fieldName;
	}
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}
	public String getFieldValue()
	{
		return fieldValue;
	}
	public void setFieldValue(String fieldValue)
	{
		this.fieldValue = fieldValue;
	}
	
	
}
