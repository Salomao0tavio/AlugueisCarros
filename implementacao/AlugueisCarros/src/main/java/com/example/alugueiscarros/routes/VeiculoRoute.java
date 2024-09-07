package com.example.alugueiscarros.routes;

import com.example.alugueiscarros.models.VeiculoModel;
import com.example.alugueiscarros.services.VeiculoService;
import com.example.alugueiscarros.utils.HttpResponseUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VeiculoRoute {

    private class VeiculoResponse {
        private final Boolean error;
        private final String message;
        private final List<VeiculoModel> veiculos;

        public VeiculoResponse(Boolean error, String message, List<VeiculoModel> veiculos) {
            this.error = error;
            this.message = message;
            this.veiculos = veiculos;
        }

        public VeiculoResponse(Boolean error, String message) {
            this(error, message, null);
        }
    }

    @PostMapping("/veiculo/new")
    public String createVeiculo(@RequestBody VeiculoModel veiculo) {
        final String[] response = new String[1];
        VeiculoService.addVeiculo(veiculo, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(true, message));
            }
        });
        return response[0];
    }

    @GetMapping("/veiculo/list")
    public String getAllVeiculos() {
        final String[] response = new String[1];
        VeiculoService.listVeiculos((success, veiculos) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(false, "Lista de veículos obtida.", veiculos));
            } else {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(true, "Erro ao obter lista de veículos."));
            }
        });
        return response[0];
    }

    @GetMapping("/veiculo/{veiculoId}")
    public String getVeiculo(@PathVariable int veiculoId) {
        final String[] response = new String[1];
        VeiculoService.getVeiculo(veiculoId, (success, veiculos) -> {
            if (success && veiculos != null && !veiculos.isEmpty()) {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(false, "Veículo obtido.", veiculos));
            } else {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(true, "Erro ao obter veículo."));
            }
        });
        return response[0];
    }

    @PutMapping("/veiculo/{veiculoId}")
    public String updateVeiculo(@PathVariable int veiculoId, @RequestBody VeiculoModel veiculo) {
        veiculo.setIdVeiculo(veiculoId);
        final String[] response = new String[1];
        VeiculoService.updateVeiculo(veiculo, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(true, message));
            }
        });
        return response[0];
    }

    @DeleteMapping("/veiculo/{veiculoId}")
    public String deleteVeiculo(@PathVariable int veiculoId) {
        final String[] response = new String[1];
        VeiculoService.deleteVeiculo(veiculoId, (success, message) -> {
            if (success) {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(false, message));
            } else {
                response[0] = HttpResponseUtil.toJson(new VeiculoResponse(true, message));
            }
        });
        return response[0];
    }
}
