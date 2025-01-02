package calculator.complex;

public class ComplexNum {
	private Component[] complex=new Component[2];
	//these are for the trignometric form: r(cos(θ)+isin(θ))
	private Double angle;
	private Double r;
	
	public ComplexNum(Double re,Double im) {
		setNormal(re,im);
		normToTrig();
	}
	
	public ComplexNum() {
		setNormal(0.0,0.0);
		normToTrig();
	}	
	
	public static ComplexNum i() {
		return new ComplexNum(0.0,1.0);
	}
	
	public boolean isEmpty() {
		return complex[0].val==0.0 && complex[1].val==0.0;
	}
	
	//for trig-> a:r, b:angle; for norm->a:re, b:im
	public ComplexNum(Double a,Double b,boolean isTrig) {
		if(isTrig) {
			setTrig(a,b);
			trigToNorm();
		}
		else {
			setNormal(a,b);
			normToTrig();
		}
	}
	
	private void setNormal(Double re,Double im) {
		this.complex[0]=new Component(re,false);
		this.complex[1]=new Component(im,true);
	}
	
	public static ComplexNum multiply(ComplexNum c1,ComplexNum c2) {
		ComplexNum result=new ComplexNum();
		Component c;
		for(int i=0;i<2;i++) {
			for(int j=0;j<2;j++) {
				c=Component.multiply(c1.complex[i],c2.complex[j]);
				result.complex[c.iPow()].val+=c.val;
			}
		}
		result.normToTrig();
		return result;
	}
	
	public void add(ComplexNum c) {
		complex[0].val+=c.re();
		complex[1].val+=c.im();
		normToTrig();
	}
	
	public static ComplexNum multiply(ComplexNum c1,ComplexNum c2,boolean isBig) {
		ComplexNum result=new ComplexNum();
		result.setTrig(c1.r()*c2.r(), c1.ang()+c2.ang());
		result.trigToNorm();
		return result;
	}
	
	public void normToTrig() {
		this.r=Math.sqrt(Math.pow(re(), 2)+Math.pow(im(), 2));
		this.angle=this.r==0?0:Math.acos(re()/r);
	}
	
	public void trigToNorm() {
		setNormal(r*Math.cos(angle),r*Math.sin(angle));
	}
	
	public Double r() {
		return this.r;
	}
	
	public Double ang() {
		return this.angle;
	}
	
	public void setTrig(Double r,Double angle) {
		this.r=r;
		this.angle=angle;
	}
	
	public Double re() {
		return this.complex[0].val;
	}
	
	public Double im() {
		return this.complex[1].val;
	}
	
	public static void main(String[] args) {
		ComplexNum c1=new ComplexNum(2.0,4.0);
		ComplexNum c2=new ComplexNum(3.2,2.5);
		ComplexNum result=ComplexNum.multiply(c1, c2);
		System.out.println(result.re()+"+"+result.im()+"i");
		System.out.println(result.re()*3);
	}
}
