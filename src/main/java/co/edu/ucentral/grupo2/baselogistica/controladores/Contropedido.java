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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.ucentral.grupo2.baselogistica.modelos.pedido;
import co.edu.ucentral.grupo2.baselogistica.repositorios.RepoPedido;
import co.edu.ucentral.grupo2.baselogistica.servicios.SerPedidos;



@RestController
@RequestMapping("api/pedidos")
public class Contropedido {

    @Autowired
    private SerPedidos pedidosServicio;

    @Autowired
    private RepoPedido repoPedido;

    private static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    //Defincion enrutamiento registrar pedido
    @PostMapping("/registroPedido")
    public ResponseEntity<pedido> guardarPedido (@ModelAttribute pedido pedido){
        pedido pedidoGuardado = pedidosServicio.guardaPedido(pedido);
        return new ResponseEntity<>(pedidoGuardado, HttpStatus.CREATED);
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

    //Ruta para subir Foto
    @PostMapping("/{id}/subirFoto")
    public ResponseEntity<String> subirFoto(@PathVariable int id, @RequestParam("foto") MultipartFile foto) {
        if (foto.isEmpty()) {
            return ResponseEntity.badRequest().body("No se ha enviado ninguna imagen.");
        }

        try {
            // Obtener el nombre original del archivo
            String nombreArchivo = foto.getOriginalFilename();

            // Definir la ruta donde se guardará la imagen
            String directorioImagenes = uploadDirectory;
            File dest = new File(directorioImagenes + nombreArchivo);

            // Guardar el archivo en el servidor
            foto.transferTo(dest);

            // Actualizar el pedido con la ruta de la imagen
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

}
