module com.revkov.spring {
	exports com.revkov.spring;
	
	opens com.revkov.spring;
	
	requires spring.boot;
	requires spring.core;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires slf4j.api;
}