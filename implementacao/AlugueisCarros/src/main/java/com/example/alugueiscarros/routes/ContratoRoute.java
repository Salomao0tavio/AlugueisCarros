package com.example.alugueiscarros.routes;

import com.example.alugueiscarros.models.ContratoModel;
import com.example.alugueiscarros.services.ContratoService;
import com.example.alugueiscarros.utils.HttpResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContratoRoute {

    private class ContratoResponse {
        private final Boolean error;
        private final String message;
        private final List<ContratoModel> contratos;

        public ContratoResponse(Boolean error, String message, List<ContratoModel> contratos) {
            this.error = error;
            this.message = message;
            this.contratos = contratos;
        }

        public ContratoResponse(Boolean error, String message) {
            this(error, message, null);
        }
    }

    @PostMapping("/contrato/new")
    public String createContrato(@RequestBody ContratoModel contrato) {
        final String[] response = new String[1];
        ContratoService.addContrato(contrato, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(true, message));
            }
        });
        return response[0];
    }

    @GetMapping("/contrato/list")
    public String getAllContratos() {
        final String[] response = new String[1];
        ContratoService.listContratos((success, contratos) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(false, "Lista de contratos obtida.", contratos));
            } else {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(true, "Erro ao obter lista de contratos."));
            }
        });
        return response[0];
    }

    @GetMapping("/contrato/{contratoId}")
    public String getContrato(@PathVariable int contratoId) {
        final String[] response = new String[1];
        ContratoService.getContrato(contratoId, (success, contratos) -> {
            if (success && contratos != null && !contratos.isEmpty()) {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(false, "Contrato obtido.", contratos));
            } else {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(true, "Erro ao obter contrato."));
            }
        });
        return response[0];
    }

    @PutMapping("/contrato/{contratoId}")
    public String updateContrato(@PathVariable int contratoId, @RequestBody ContratoModel contrato) {
        contrato.setId(contratoId);
        final String[] response = new String[1];
        ContratoService.updateContrato(contrato, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(true, message));
            }
        });
        return response[0];
    }

    @DeleteMapping("/contrato/{contratoId}")
    public String deleteContrato(@PathVariable int contratoId) {
        final String[] response = new String[1];
        ContratoService.deleteContrato(contratoId, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new ContratoResponse(true, message));
            }
        });
        return response[0];
    }
}
