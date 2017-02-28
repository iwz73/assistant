package idv.hsiehpinghan.springintegrationassistant.service.impl;

import org.springframework.stereotype.Service;

import idv.hsiehpinghan.springintegrationassistant.service.BasicChannelService;

@Service
public class BasicChannelServiceImpl implements BasicChannelService {

	@Override
	public void method_0(String message) {
		System.err.println("message : " + message);
	}

}
