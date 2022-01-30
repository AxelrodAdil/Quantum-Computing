package Chapter_2;

import com.gluonhq.strangefx.render.Renderer;
import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.X;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;

public class chapter_2_ui {
    /**
     *
     * Визуализировать очень простую квантовую схему с помощью Strange (вентиль Паули-X).
     *
     */

    public static void main(String[] args) {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step = new Step();
        step.addGate(new X(0));
        program.addStep(step);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        int value = zero.measure();
        System.out.println("Value = "+value);
        Renderer.renderProgram(program);
    }
}