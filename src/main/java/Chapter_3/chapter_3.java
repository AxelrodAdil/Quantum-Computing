package Chapter_3;

import com.gluonhq.strange.*;
import com.gluonhq.strange.gate.Hadamard;
import com.gluonhq.strange.local.SimpleQuantumExecutionEnvironment;
import com.gluonhq.strangefx.render.Renderer;

public class chapter_3 {
    /**
     *
     * "Quantum Computing in Action" Johan Vos, January 2022
     *
     * https://en.wikipedia.org/wiki/Quantum_logic_gate
     * https://www.quantum-inspire.com/kbase/hadamard/
     * https://qiskit.org/textbook/ch-states/single-qubit-gates.html
     *
     * Кубит в своем базовом состоянии, кубит перейдет в чистое состояние,
     * в котором есть равные шансы измерить 1 или 0.
     *
     */

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Single run of a Quantum Circuit with Hadamard Gate");
        singleExecution();
        System.out.println("==================================================");
        System.out.println("\n\n");
        System.out.println("==================================================");
        System.out.println("FiveTimes run of a Quantum Circuit with Hadamard Gate");
        FiveTimesExecution();
        System.out.println("==================================================");
        System.out.println("\n\n");
        System.out.println("==================================================");
        System.out.println("1000 runs of a Quantum Circuit with Hadamard Gate");
        manyExecution();
        System.out.println("==================================================");
    }

    public static void singleExecution() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);
        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        Qubit zero = qubits[0];
        int value = zero.measure();
        System.out.println("Value = "+value);
        Renderer.renderProgram(program);
    }

    public static void FiveTimesExecution() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(5);
        Step step = new Step();
        for(int i=0; i<5; i++){
            step.addGate(new Hadamard(i));
            program.addStep(step);
        }

        Result result = simulator.runProgram(program);
        Qubit[] qubits = result.getQubits();
        for(Qubit q : qubits){
            int value = q.measure();
            System.out.println("Value = " + value);
        }
        Renderer.renderProgram(program);
    }

    public static void manyExecution() {
        QuantumExecutionEnvironment simulator = new SimpleQuantumExecutionEnvironment();
        Program program = new Program(1);
        Step step = new Step();
        step.addGate(new Hadamard(0));
        program.addStep(step);
        int cntZero = 0;
        int cntOne = 0;
        for (int i = 0; i < 1000; i++) {
            Result result = simulator.runProgram(program);
            Qubit[] qubits = result.getQubits();
            Qubit zero = qubits[0];
            int value = zero.measure();
            if (value == 0) cntZero++;
            if (value == 1) cntOne++;
        }
        System.out.println("Applied Hadamard circuit 1000 times, got "
                + cntZero + " times 0 and " + cntOne + " times 1.");
    }
}