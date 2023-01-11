package io.devfactory.web.member.api;

import io.devfactory.web.member.dto.MemberMapper;
import io.devfactory.web.member.dto.request.MemberCreateRequestView;
import io.devfactory.web.member.dto.request.MemberModifyRequestView;
import io.devfactory.web.member.dto.response.MemberResponseView;
import io.devfactory.web.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import static io.devfactory.global.util.UriUtils.buildUriToRetrieveById;

@SuppressWarnings("squid:S112")
@RequestMapping("/api/members")
@RestController
public class MemberApi {

  private final MemberService memberService;

  public MemberApi(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public ResponseEntity<List<MemberResponseView>> retrieveMembers() {
    final var foundMembers = memberService.findMembers();
    return ResponseEntity.ok(MemberMapper.INSTANCE.toList(foundMembers));
  }

  @GetMapping("/query")
  public ResponseEntity<List<MemberResponseView>> retrieveMembersWithQuery() {
    final var foundMembers = memberService.findMembersWithQuery();
    return ResponseEntity.ok(MemberMapper.INSTANCE.toList(foundMembers));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberResponseView> retrieveMember(@PathVariable("id") Long id) {
    final var foundMember = memberService.findMember(id);
    return ResponseEntity.ok(MemberMapper.INSTANCE.toView(foundMember));
  }

  @PostMapping
  public ResponseEntity<Map<String, Long>> createMember(
      @Valid @RequestBody MemberCreateRequestView requestView) {
    final var savedId = memberService.saveMember(requestView.toMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PostMapping("/ex")
  public ResponseEntity<Map<String, Long>> createMemberException(
      @Valid @RequestBody MemberCreateRequestView requestView) throws Exception {
    final var savedId = memberService.saveMemberException(requestView.toMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PostMapping("/run-ex")
  public ResponseEntity<Map<String, Long>> createMemberRuntimeException(
      @Valid @RequestBody MemberCreateRequestView requestView) {
    final var savedId = memberService.saveMemberRuntimeException(requestView.toMember());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Long>> modifyMember(@PathVariable("id") Long id,
      @Valid @RequestBody MemberModifyRequestView requestView) {
    final var changedId = memberService.changeMember(id, requestView.toMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}")
  public ResponseEntity<Map<String, Long>> modifyMemberWithQuery(@PathVariable("id") Long id,
      @Valid @RequestBody MemberModifyRequestView requestView) {
    final var changedId = memberService.changeMemberWithQuery(id, requestView.toMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}/ex")
  public ResponseEntity<Map<String, Long>> modifyMemberWithQueryException(
      @PathVariable("id") Long id,
      @Valid @RequestBody MemberModifyRequestView requestView) throws Exception {
    final var changedId = memberService.changeMemberWithQueryException(id, requestView.toMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}/run-ex")
  public ResponseEntity<Map<String, Long>> modifyMemberWithQueryRuntimeException(
      @PathVariable("id") Long id, @Valid @RequestBody MemberModifyRequestView requestView) {
    final var changedId = memberService.changeMemberWithQueryRuntimeException(id, requestView.toMember());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeMember(@PathVariable("id") Long id) {
    memberService.deleteMember(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/query/{id}")
  public ResponseEntity<Object> removeMemberWithQuery(@PathVariable("id") Long id) {
    memberService.deleteMemberWithQuery(id);
    return ResponseEntity.noContent().build();
  }

}
