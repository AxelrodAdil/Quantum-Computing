package Chapter_2;

import org.redfx.strange.*;
import org.redfx.strange.gate.X;
import org.redfx.strange.local.SimpleQuantumExecutionEnvironment;

public class chapter_2 {
    /**
     * "Quantum Computing in Action" Johan Vos, January 2022
     *
     * Создать простейшую квантовую схему с помощью Strange.
     * Он состоит из одного кубита и одного вентиля (вентиль Паули-X), который применяется к этому кубиту.
     *
     * https://habr.com/ru/company/microsoft/blog/351628/
     *
     */
    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(5);
        Step step = new Step();
        step.addGate(new X(0));
        program.addStep(step);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        for (Qubit q : qubits){
            int value = q.measure();
            System.out.println("Value = "+value);
        }
    }
}