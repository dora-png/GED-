package com.microservice.ged.service;

import org.springframework.data.domain.Page;

import com.microservice.ged.beans.LogPoste;

public interface LogPosteService {
	public Page<LogPoste> logUser(String loginuser, int page, int size) throws Exception ;
	public Page<LogPoste> logPoste(String namePoste, int page, int size) throws Exception ;
	public Page<LogPoste> logType(String type, int page, int size) throws Exception ;
	public Page<LogPoste> logObject(String objectname, int page, int size) throws Exception ;
	public Page<LogPoste> logObjectAndType(String objectname, String type, int page, int size) throws Exception ;
	public Page<LogPoste> logPosteAndType(String postename, String type, int page, int size) throws Exception ;
	public void add(LogPoste logPosteUser) throws Exception ;
}
