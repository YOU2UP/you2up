package school.sptech.apitreinoyou2up;

import org.springframework.web.bind.annotation.*;

import javax.swing.tree.TreeNode;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/treinos")
@RestController
public class TreinoController {

    List<Treino> treinos = new ArrayList<>();


    public TreinoController() {
    }

    @PostMapping("/ar-livre")
    public Treino cadastrarTreinoArLivre(@RequestBody TreinoArLivre treino){
        treinos.add(treino);
        return treino;
    }

    @PostMapping("/academia")
    public Treino cadastrarTreinoAcademia(@RequestBody TreinoAcademia treino){
        treinos.add(treino);
        return treino;
    }

    @GetMapping
    public List<Treino> mostrarTreinos(){
        return treinos;
    }

    @GetMapping("/calorias")
    public double gastoCaloricoTotal(){
        double total = 0;

        for(Treino t : treinos) {
            total += t.getGastoCalorico();
        }

        return total;
    }

}
