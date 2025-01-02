package calculator.complex;

import java.util.Optional;

public class Component {
	public Double val;
	//the power of i of this component
	private Integer iPow;
	
	public Component(Double val,boolean isIm) {
		set(Optional.ofNullable(val).orElse(0.0),isIm?1:0);
	}
	
	public Component(boolean isIm) {
		set(0.0,isIm?1:0);
	}
	
	public void updateI(Integer i) {
		this.iPow+=i;
		this.iPow%=4;
		if(iPow>1) {
			set(-val,this.iPow-2);
		}
	}
	
	public static Component multiply(Component c1,Component c2) {
		Component result=c1.clone();
		result.val*=c2.val;
		result.updateI(c2.iPow());
		return result;
	}
	
	public Component clone() {
		return new Component(this.val,iPow==1?true:false);
	}
	
	private void set(Double val,Integer iPow) {
		this.val=val;
		this.iPow=iPow;
	}
	
	public Integer iPow() {
		return iPow;
	}
}
