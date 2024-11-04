package SegundaEntrega.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SegundaEntrega.api.DTO.FangoCreateDTO;
import SegundaEntrega.api.DTO.FangoDTO;
import SegundaEntrega.api.services.FangoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import utils.ApiResponseMsg;

@RestController
@RequestMapping("/api/tiendas")
public class FangoController {
    @Autowired
    private final FangoService tiendaService;

    public FangoController(FangoService tiendaService) {
        this.tiendaService = tiendaService;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todas las tiendas de fango", description = "Retorna todas las tiendas de fango")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = FangoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Stores not found", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                    @ExampleObject(name = "TiendaNotFound", value = "{\"message\": \"Shop not found\"}", description = "Tiendas no encontradas")
            }))
    })
    public ResponseEntity<List<FangoDTO>> getAllTiendas(@RequestParam(value = "includeRelations", defaultValue = "false") boolean includeRelations) {
        return ResponseEntity.ok(tiendaService.getAllTiendas(true)); // Aquí puedes forzar includeRelations a true para la prueba
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una tienda por su id", description = "Retorna la tienda asociada al id y sus relaciones")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = FangoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "TiendaNotFound", value = "{\"message\": \"Store not found\"}", description = "Tienda no encontrada")
        }))
    })
    public ResponseEntity<?> getTiendaById(@PathVariable("id") Long id){
        try {
            Optional<FangoDTO> tienda = tiendaService.getTiendaById(id, false);
            return ResponseEntity.ok(tienda);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("usuario no encontrado", e.getMessage()));
        }
    }

    @PostMapping("/createTienda")
    @Operation(summary = "Crear una tienda", description = "Retorna la tienda creada")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = FangoCreateDTO.class))),
        @ApiResponse(responseCode = "404", description = "Store not created", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "TiendaNotCreated", value = "{\"message\": \"Store not created\"}", description = "Tienda no se pudo crear")
        }))
    })
    public ResponseEntity<FangoDTO> createTienda(@RequestBody FangoCreateDTO tiendaCreateDTO) {
        FangoDTO createdTienda = tiendaService.saveTienda(tiendaCreateDTO);
        return ResponseEntity.ok(createdTienda);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Borra una tienda por su id asociado", description = "Retorna mensaje tienda eliminada")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = FangoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Store could not be deleted", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "TiendaNotDeleted", value = "{\"message\": \"Store not deleted\"}", description = "Tienda no borrada")
        }))
    })
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        try {
            tiendaService.deleteTienda(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("tienda eliminada", id));
            } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar la tienda", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifica los campos de una tienda por su id", description = "Retorna la tienda modificada.")
    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = FangoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Store not found", content = @Content(schema = @Schema(implementation = ApiResponse.class), examples = {
                @ExampleObject(name = "TiendaNotFound", value = "{\"message\": \"Store not found\"}", description = "Tienda no encontrada ni modificada")
        }))
    })
    public ResponseEntity<FangoDTO> updateTienda(@PathVariable Long id, @RequestBody FangoCreateDTO tiendaCreateDTO) {
        FangoDTO updatedTienda = tiendaService.updateTienda(id, tiendaCreateDTO);
        return ResponseEntity.ok(updatedTienda);
    }
}