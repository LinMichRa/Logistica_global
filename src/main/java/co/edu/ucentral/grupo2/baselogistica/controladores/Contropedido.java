package co.edu.ucentral.grupo2.baselogistica.controladores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ucentral.grupo2.baselogistica.modelos.despachador;
import co.edu.ucentral.grupo2.baselogistica.modelos.pedido;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoPedido;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerDespachador;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerPedidos;

@RestController
@RequestMapping("api/pedidos")
public class Contropedido {

    @Autowired
    private SerPedidos pedidosServicio;

    @Autowired
    private RepoPedido repoPedido;

    @Autowired
    private SerDespachador serDespachador;

    private static String uploadDirectory = System.getProperty("user.dir") + "uploads";

    @PostMapping("/registroPedido")
    public ResponseEntity<?> guardarPedido(@RequestBody pedido pedido) {
    try {
        // Validar que el adminId esté presente
        if (pedido.getAdmin() == null || pedido.getAdmin().getCedula() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El adminId es obligatorio");
        }

        // Busca al administrador por ID
        Long adminId = pedido.getAdmin().getCedula();
        Optional<despachador> adminOptional = serDespachador.buscarDespachadorPorCedula(adminId);

        if (adminOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado");
        }

        // Asocia el administrador al pedido
        pedido.setAdmin(adminOptional.get());

        // Guarda el pedido con el administrador asociado
        pedido savedPedido = pedidosServicio.guardaPedido(pedido);

        return ResponseEntity.ok(savedPedido);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el pedido");
    }
}



    //Ruta para guardar la modificacioin del pedido
    @PostMapping("/modificarPedido/{id}")
    public ResponseEntity<pedido>modificarPedido(@PathVariable("id") Integer id, @ModelAttribute pedido pedido){
        pedido pedidoModificado = pedidosServicio.modificarPedido(pedido);

        return new ResponseEntity<>(pedidoModificado, HttpStatus.OK);
    }

    //mostrar TODOS los pedidos registrdos en la base de datos
    @GetMapping("/mostrarPedido")
    public ResponseEntity<List<pedido>> buscarPedido() {
        List<pedido> pedido = pedidosServicio.buscarPedido();
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/{id}/subirFoto")
    public ResponseEntity<String> subirFoto(@PathVariable int id, @RequestParam("foto") MultipartFile foto) {
        if (foto.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha enviado ninguna imagen.");
        }

        try {
            // Obtener el nombre original del archivo
            String nombreArchivo = foto.getOriginalFilename();

            // Obtener la ruta absoluta del proyecto y agregar la carpeta "uploads"
            String directorioImagenes = System.getProperty("user.dir") + File.separator + "uploads";
            
            // Crear la carpeta "uploads" si no existe
            File directorio = new File(directorioImagenes);
            if (!directorio.exists()) {
                directorio.mkdirs(); // Crea el directorio si no existe
            }

            // Definir la ruta completa donde se guardará la imagen
            File destino = new File(directorioImagenes + File.separator + nombreArchivo);

            // Guardar el archivo en el servidor
            foto.transferTo(destino);

            // Actualizar el pedido con la ruta de la imagen (nombre del archivo)
            pedidosServicio.actualizarFotoPedido(id, nombreArchivo);

            return ResponseEntity.ok("Imagen subida exitosamente para el pedido con ID: " + id);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al subir la imagen.");
        }
    }


    @GetMapping("/pedidos/{id}/foto")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) throws IOException {
        Optional<pedido> pedidoOpt = repoPedido.findById(id);
        if (pedidoOpt.isPresent()) {
            pedido pedido = pedidoOpt.get();
            Path path = Paths.get(pedido.getFoto());
            byte[] imageBytes = Files.readAllBytes(path);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarPedidoPorID/{id}")
    public ResponseEntity<?> buscarPedidoPorID(@PathVariable("id") int id){
        Optional <pedido> pedidoOptional = pedidosServicio.buscarPedidoPorID(id);

        if(pedidoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoOptional.get());
    }

    // Controlador de Pedidos

    // Contar pedidos sin conductor
    @GetMapping("/pendientes/count")
    public ResponseEntity<Long> contarPedidosSinConductor() {
        Long count = pedidosServicio.contarPedidosSinConductor();
        return ResponseEntity.ok(count);
    }

    // Obtener lista de pedidos sin conductor
    @GetMapping("/pendientes")
    public ResponseEntity<List<pedido>> obtenerPedidosSinConductor() {
        List<pedido> pendientes = pedidosServicio.obtenerPedidosSinConductor();
        return ResponseEntity.ok(pendientes);
    }

    // Asignar conductor a un pedido
    @PutMapping("/{id}/asignarConductor")
    public ResponseEntity<?> asignarConductor(
            @PathVariable Long id,
            @RequestParam Long conductorId) {
        try {
            pedidosServicio.asignarConductor(id, conductorId);
            return ResponseEntity.ok("Conductor asignado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/admin/{adminId}")
    public List<pedido> obtenerPedidosPorAdmin(@PathVariable Long adminId) {
        return pedidosServicio.obtenerPedidosPorAdmin(adminId);
    }

    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<?> buscarPedidoPorTracking(@PathVariable String trackingNumber) {
        try {
            pedido pedido = pedidosServicio.buscarPorNumeroTracking(trackingNumber);
            if (pedido != null) {
                return ResponseEntity.ok(pedido);
            } else {
                return ResponseEntity.status(404).body("Pedido no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
