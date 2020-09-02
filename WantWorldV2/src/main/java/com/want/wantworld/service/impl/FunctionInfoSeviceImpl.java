package com.want.wantworld.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.wantworld.dao.FunctionGroupDao;
import com.want.wantworld.dao.FunctionInfoDao;
import com.want.wantworld.dao.LoginTokenMapper;
import com.want.wantworld.entity.FunctionInfo;
import com.want.wantworld.entity.LoginToken;
import com.want.wantworld.service.FunctionInfoService;
import com.want.wantworld.utils.WResultUtil;
import com.want.wantworld.vo.Menu;
import com.want.wantworld.vo.MenuGroup;
import com.want.wantworld.vo.WResult;


@Service
public class FunctionInfoSeviceImpl implements FunctionInfoService {
	
	@Autowired
	private FunctionInfoDao functionInfoDao;
	
	@Autowired
	private LoginTokenMapper loginTokenMapper;
	
	@Autowired
	private FunctionGroupDao functionGroupDao;

	@Override
	public FunctionInfo getFunctionInfo(String sid) {
		return functionInfoDao.selectByPrimaryKey(sid);
	}

	@Override
	public WResult getMenuGroupByEmpId(String token) {
		LoginToken loginToken = new LoginToken();
		loginToken.setToken(token);
		
		LoginToken loginTokenParam = loginTokenMapper.selectOneByParam(loginToken);//根据token查询员工ID
		String empId = loginTokenParam.getEmpId();//员工ID
		
		List<MenuGroup> muenGroupList = functionGroupDao.selectMenuGroupList(empId);//根据人员查询有权限的菜单组列表
		List<Menu> menuList = functionInfoDao.selectMentListByEmpId(empId);//查询员工有权限的所有子菜单
		for(MenuGroup menuGroup : muenGroupList){
			String grupId = menuGroup.getGroupId();
			//过滤该菜单组下的子菜单
			List<Menu> childList = menuList.stream().filter(m -> m.getParentId().equals(grupId)).collect(Collectors.toList());
			menuGroup.setMenus(childList);
		}
		return  WResultUtil.ok(muenGroupList) ;
	}
	

}
