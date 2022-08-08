package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ged.beans.GroupProfile;
import com.microservice.ged.beans.GroupUser;
import com.microservice.ged.beans.Profiles;

@Repository
public interface GroupProfileRepo extends JpaRepository<GroupProfile, Long>{
	GroupProfile findByIdgroupprofileAndIsactiveTrue(Long idgroupprofile);
	Page<GroupProfile> findByIsactiveTrue(Pageable pageable);
	Page<GroupProfile> findByGroupuserIdAndIsactiveTrue(GroupUser groupuserId, Pageable pageable);
	Page<GroupProfile> findByGroupuserId(GroupUser groupuserId, Pageable pageable);
	GroupProfile findByGroupuserIdAndProfileIdAndIsactiveTrue(GroupUser groupuserId, Profiles profilesId);
	List<GroupProfile> findByGroupuserId(GroupUser groupuserId);
	Page<GroupProfile> findByProfileId(Profiles profilesId, Pageable pageable);
	Page<GroupProfile> findByProfileIdAndIsactiveTrue(Profiles profilesId, Pageable pageable);
	List<GroupProfile> findByProfileIdAndIsactiveTrue(Profiles profilesId);

}
