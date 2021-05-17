package io.devfactory.web.member.api;

import io.devfactory.web.member.dto.MemberMapper;
import io.devfactory.web.member.dto.request.MemberCreateRequestView;
import io.devfactory.web.member.dto.request.MemberModifyRequestView;
import io.devfactory.web.member.dto.response.MemberResponseView;
import io.devfactory.web.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static io.devfactory.global.util.UriUtils.buildUriToRetrieveById;

@RequestMapping("/api/members")
@RestController
public class MemberApi {

  private final MemberService memberService;

  public MemberApi(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public ResponseEntity<List<MemberResponseView>> retrieveMembers() {
    final var findMembers = memberService.findMembers();
    return ResponseEntity.ok(MemberMapper.INSTANCE.toList(findMembers));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberResponseView> retrieveMember(@PathVariable("id") Long id) {
    final var findMember = memberService.findMember(id);
    return ResponseEntity.ok(MemberMapper.INSTANCE.toView(findMember));
  }

  @PostMapping
  public ResponseEntity<Map<String, Long>> createMember(
      @Valid @RequestBody MemberCreateRequestView requestView) {
    final var savedId = memberService.saveMember(requestView.toMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Long>> modifyMember(@PathVariable("id") Long id,
      @Valid @RequestBody MemberModifyRequestView requestView) {
    final var changedId = memberService.changeMember(id, requestView.toMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeMember(@PathVariable("id") Long id) {
    memberService.deleteMember(id);
    return ResponseEntity.noContent().build();
  }

}
