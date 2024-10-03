package com.be.member.mapper;

import com.be.member.domain.MemberRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRoleMapper {

    int save(MemberRole memberRoleDto);
    List<MemberRole> findMemberRoleByMemberNum(long memberNum);
}
