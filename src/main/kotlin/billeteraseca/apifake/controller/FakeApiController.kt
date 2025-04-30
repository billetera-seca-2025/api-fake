package billeteraseca.apifake.controller

import billeteraseca.apifake.dto.DebinRequest
import billeteraseca.apifake.service.FakeApiService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mock")
class FakeApiController(private val fakeApiService: FakeApiService) {

    @PostMapping("/debin")
    fun simulateDebin(@RequestBody request: DebinRequest): ResponseEntity<Boolean> {
        val approved = fakeApiService.processDebinRequest(request)
        return ResponseEntity.ok(approved)
    }
}
