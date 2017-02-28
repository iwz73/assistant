package idv.hsiehpinghan.springintegrationassistant.service.impl;

import org.springframework.stereotype.Service;

import idv.hsiehpinghan.springintegrationassistant.service.BasicGatewayService;

@Service
public class BasicGatewayServiceImpl implements BasicGatewayService {

	@Override
	public String method_0(String message) {
		return message.toUpperCase();
	}

}
