package com.want.service;

import java.util.List;

import com.want.dto.ResponseEntity;
import com.want.vo.Attence;

public interface IAttenceWebService {
	
	ResponseEntity accessAttence(List<Attence> list);
	
}
