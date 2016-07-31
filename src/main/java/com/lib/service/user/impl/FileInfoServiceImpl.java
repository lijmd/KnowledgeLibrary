package com.lib.service.user.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.dao.FileInfoDao;
import com.lib.entity.FileInfo;
import com.lib.entity.UserInfo;
import com.lib.service.user.FileInfoService;
import com.lib.utils.CompressUtil;

@Service
public class FileInfoServiceImpl implements FileInfoService {

	@Autowired
	private FileInfoDao fileinfoDao;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public int insertFile(FileInfo fileInfo) {

		return fileinfoDao.insert(fileInfo);
	}

	@Override
	public List<String> compressFile(String name, UserInfo user) throws Exception {
		List<String> uuids = new ArrayList<String>();
		List<FileInfo> files = CompressUtil.startCompress(name, user.getUserId());
		try {
			FileUtils.forceDelete(new File(name));
		} catch (Exception e) {
			LOG.error("删除文件失败" + name);
		}
//		for (FileInfo f : files) {
//			f.setFileUserId(user.getUserId());
//			f.setFileClassId(1l);
//			f.setFileBrief("无");
//			fileinfoDao.insert(f);
//			uuids.add(f.getFileUuid());
//		}
		return uuids;
	}

}