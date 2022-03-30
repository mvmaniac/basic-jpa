package io.devfactory.web.member.dto;

import io.devfactory.web.member.domain.mysql.Member;
import io.devfactory.web.member.dto.request.MemberCreateRequestView;
import io.devfactory.web.member.dto.request.MemberModifyRequestView;
import io.devfactory.web.member.dto.response.MemberResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = MemberTranslatorMapper.class)
public interface MemberMapper {

  MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

  Member createViewOf(MemberCreateRequestView createRequestView);

  Member modifyViewOf(MemberModifyRequestView modifyRequestView);

  MemberResponseView toView(Member member);

  List<MemberResponseView> toList(List<Member> members);

}
