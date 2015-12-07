package ma.eventmanager.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ma.eventmanager.model.StateVo;
import ma.eventmanager.model.TagVo;

@Entity
public class State{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	public State(){}
	
	

	public State(String name){
		this.name = name;
	}

	public State(StateVo state){
		
		if(state.getId() != null && !"".equals(state.getId()) && !"null".equals(state.getId())){
			this.id = Integer.parseInt(state.getId());	
		}
		this.name = state.getName();
	}

	public StateVo toStateVo(){
		StateVo stateVo = new StateVo();
		stateVo.setId(this.getId()+"");
		stateVo.setName(this.getName());
		return stateVo;
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
