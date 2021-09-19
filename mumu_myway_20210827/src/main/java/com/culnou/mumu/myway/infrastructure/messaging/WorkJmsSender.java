package com.culnou.mumu.myway.infrastructure.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.WorkSaved;
import com.culnou.mumu.myway.domain.model.WorkSender;

@Service("workJmsSender")
@Transactional
public class WorkJmsSender implements WorkSender{
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendWork(WorkSaved workSaved) {
		
		jmsTemplate.convertAndSend("mumuQueue", workSaved);
	}

}
