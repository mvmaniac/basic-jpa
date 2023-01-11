package io.devfactory.web.team.api;

import io.devfactory.web.team.dto.TeamMapper;
import io.devfactory.web.team.dto.request.TeamCreateRequestView;
import io.devfactory.web.team.dto.request.TeamModifyRequestView;
import io.devfactory.web.team.dto.response.TeamResponseView;
import io.devfactory.web.team.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import static io.devfactory.global.util.UriUtils.buildUriToRetrieveById;

@SuppressWarnings("squid:S112")
@RequestMapping("/api/teams")
@RestController
public class TeamApi {

  private final TeamService teamService;

  public TeamApi(TeamService teamService) {
    this.teamService = teamService;
  }

  @GetMapping
  public ResponseEntity<List<TeamResponseView>> retrieveBoards() {
    final var foundTeams = teamService.findTeams();
    return ResponseEntity.ok(TeamMapper.INSTANCE.toList(foundTeams));
  }

  @GetMapping("/query")
  public ResponseEntity<List<TeamResponseView>> retrieveBoardsWithQuery() {
    final var foundTeams = teamService.findTeamsWitQuery();
    return ResponseEntity.ok(TeamMapper.INSTANCE.toList(foundTeams));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeamResponseView> retrieveBoard(@PathVariable("id") Long id) {
    final var foundTeam = teamService.findTeam(id);
    return ResponseEntity.ok(TeamMapper.INSTANCE.toView(foundTeam));
  }

  @PostMapping
  public ResponseEntity<Map<String, Object>> createBoard(
      @Valid @RequestBody TeamCreateRequestView requestView) {
    final var savedId = teamService.createTeam(requestView.toTeam());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PostMapping("/ex")
  public ResponseEntity<Map<String, Object>> createBoardException(
      @Valid @RequestBody TeamCreateRequestView requestView) throws Exception {
    final var savedId = teamService.createTeamException(requestView.toTeam());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PostMapping("/run-ex")
  public ResponseEntity<Map<String, Object>> createBoardRuntimeException(
      @Valid @RequestBody TeamCreateRequestView requestView) {
    final var savedId = teamService.createTeamRuntimeException(requestView.toTeam());
    return ResponseEntity.created(buildUriToRetrieveById(savedId)).body(Map.of("id", savedId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> modifyBoard(@PathVariable("id") Long id,
      @Valid @RequestBody TeamModifyRequestView requestView) {
    final var changedId = teamService.changeTeam(id, requestView.toTeam());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}")
  public ResponseEntity<Map<String, Object>> modifyBoardWithQuery(@PathVariable("id") Long id,
      @Valid @RequestBody TeamModifyRequestView requestView) {
    final var changedId = teamService.changeTeamWithQuery(id, requestView.toTeam());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}/ex")
  public ResponseEntity<Map<String, Object>> modifyBoardWithQueryException(
      @PathVariable("id") Long id,
      @Valid @RequestBody TeamModifyRequestView requestView) throws Exception {
    final var changedId = teamService.changeTeamWithQueryException(id, requestView.toTeam());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @PutMapping("/query/{id}/run-ex")
  public ResponseEntity<Map<String, Object>> modifyBoardWithQueryRuntimeException(
      @PathVariable("id") Long id, @Valid @RequestBody TeamModifyRequestView requestView) {
    final var changedId = teamService.changeTeamWithQueryRuntimeException(id, requestView.toTeam());
    return ResponseEntity.ok(Map.of("id", changedId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeBoard(@PathVariable("id") Long id) {
    teamService.deleteTeam(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/query/{id}")
  public ResponseEntity<Object> removeBoardWithQuery(@PathVariable("id") Long id) {
    teamService.deleteTeamWithQuery(id);
    return ResponseEntity.noContent().build();
  }

}
