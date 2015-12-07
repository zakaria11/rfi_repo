package ma.eventmanager.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ma.eventmanager.model.TagVo;

@Entity
public class Tag
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	
	

	public Tag()
	{
		super();
	}

	
	
	public Tag(String name)
	{
		super();
		this.name = name;
	}

	public Tag(TagVo tag){
		if(tag.getId() != null && !"".equals(tag.getId()) && !"null".equals(tag.getId())){
			this.id = Integer.parseInt(tag.getId());	
		}
		this.name = tag.getName();
	}
	
	public TagVo toTagVo(){
		TagVo tagVo = new TagVo();
		tagVo.setId(this.getId()+"");
		tagVo.setName(this.getName());
		return tagVo;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
