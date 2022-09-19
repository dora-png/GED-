package com.microservice.ged.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.microservice.ged.beans.DroitGroups;
import com.microservice.ged.beans.Droits;
import com.microservice.ged.beans.GroupUser;

@Repository
public interface DroitGroupsRepo extends JpaRepository<DroitGroups, Long>{
	DroitGroups findByIddroitgroups(Long iddroitgroups);
	Page<DroitGroups> findByIsactiveTrue(Pageable pageable);
	Page<DroitGroups> findByIsactiveFalse(Pageable pageable);
	Page<DroitGroups> findByDroitIdAndIsactiveTrue(Droits droitId, Pageable pageable);
	DroitGroups findByDroitIdAndGroupuserIdAndIsactiveTrue(Droits droitId, GroupUser groupuserId);
	Page<DroitGroups> findByGroupuserId(GroupUser groupuserId, Pageable pageable);
	Page<DroitGroups> findByGroupuserIdAndIsactiveTrue(GroupUser groupuserId, Pageable pageable);
	Page<DroitGroups> findByGroupuserIdAndIsactiveFalse(GroupUser groupuserId, Pageable pageable);
	List<DroitGroups> findByGroupuserId(GroupUser groupuserId);
	List<DroitGroups> findByGroupuserIdAndIsactiveTrue(GroupUser groupuserId);
	List<DroitGroups> findByGroupuserIdAndIsactiveFalse(GroupUser groupuserId);

}
