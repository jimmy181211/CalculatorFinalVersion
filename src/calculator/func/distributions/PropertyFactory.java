package calculator.func.distributions;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PropertyFactory {
	private Distribution distribObj;
	
	public DistribProperties getProxyObject() {
		DistribType distrib=distribObj.getDistrib();
		
		DistribProperties proxyObj=(DistribProperties) Proxy.newProxyInstance(
			//load the proxy class 	
			distrib.getClass().getClassLoader(),
			distrib.getClass().getInterfaces(),
			//the calling function of the proxy object
			new InvocationHandler() {
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
					
				}
			}
		);
	}
}
