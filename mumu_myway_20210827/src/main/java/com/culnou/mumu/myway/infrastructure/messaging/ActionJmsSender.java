package com.culnou.mumu.myway.infrastructure.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culnou.mumu.myway.domain.model.ActionRemoved;
import com.culnou.mumu.myway.domain.model.ActionSender;
@Service("actionJmsSender")
@Transactional
public class ActionJmsSender implements ActionSender {
	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void removeAction(ActionRemoved actionRemoved) throws Exception {
		// TODO Auto-generated method stub
		jmsTemplate.convertAndSend("removeActionQueue", actionRemoved);

	}

}
