package frsf.cidisi.exercise.search;

import frsf.cidisi.exercise.actions.Bajar;
import frsf.cidisi.exercise.actions.IrEste;
import frsf.cidisi.exercise.actions.IrNorEste;
import frsf.cidisi.exercise.actions.IrNorOeste;
import frsf.cidisi.exercise.actions.IrNorte;
import frsf.cidisi.exercise.actions.IrOeste;
import frsf.cidisi.exercise.actions.IrSur;
import frsf.cidisi.exercise.actions.IrSurEste;
import frsf.cidisi.exercise.actions.IrSurOeste;
import frsf.cidisi.exercise.actions.Subir;


import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.solver.search.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

public class AgentDrone extends SearchBasedAgent {

    public AgentDrone() {

        // The Agent Goal
        GoalDrone agGoal = new GoalDrone();

        // The Agent State
        StateDrone agState = new StateDrone();
        this.setAgentState(agState);

        // Create the operators
        Vector<SearchAction> operators = new Vector<SearchAction>();
        operators.addElement(new IrNorte());	
        operators.addElement(new IrSur());	
        operators.addElement(new IrEste());	
        operators.addElement(new IrOeste());	
        operators.addElement(new IrNorEste());	
        operators.addElement(new IrNorOeste());	
        operators.addElement(new IrSurEste());	
        operators.addElement(new IrSurOeste());	
        operators.addElement(new Bajar());	
        operators.addElement(new Subir());	

        // Create the Problem which the agent will resolve
        Problem problem = new Problem(agGoal, agState, operators);
        this.setProblem(problem);
        
        //System.out.println("PROBLEMA: \n");
       // System.out.println("Acciones: " + problem.getActions().toString());
        
       // System.out.println("\n\nAcciones: " + problem.getAgentState().toString());
    }

    /**
     * This method is executed by the simulator to ask the agent for an action.
     */
    @Override
    public Action selectAction() {

    	
        
    	/*Costo uniforme*/
    	/*CostFunction f = new CostFunction();
    	UniformCostSearch strategy = new  UniformCostSearch(f);
    	*/
    	
    	/* Create the search strategy*/
    	DepthFirstSearch strategy = new DepthFirstSearch();  
        
    	/*A estrella*/
    	/*CostFunction g=new CostFunction(); 
    	Heuristic h=new Heuristic();
        AStarSearch strategy=new AStarSearch(g,h);
    	*/
    	
    	 /**
         * Another search strategy examples:
         * 
         * Depth First Search:
         * DepthFirstSearch strategy = new DepthFirstSearch();
         * 
         * Breath First Search:
         * BreathFirstSearch strategy = new BreathFirstSearch();
         * 
         * Uniform Cost:
         * IStepCostFunction costFunction = new CostFunction();
         * UniformCostSearch strategy = new UniformCostSearch(costFunction);
         * 
         * A Star Search:
         * IStepCostFunction cost = new CostFunction();
         * IEstimatedCostFunction heuristic = new Heuristic();
         * AStarSearch strategy = new AStarSearch(cost, heuristic);
         * 
         * Greedy Search:
         * IEstimatedCostFunction heuristic = new Heuristic();
         * GreedySearch strategy = new GreedySearch(heuristic);
         */
    	        

        // Create a Search object with the strategy
                
        Search searchSolver = new Search(strategy);

        /* Generate an XML file with the search tree. It can also be generated
         * in other formats like PDF with PDF_TREE */
        searchSolver.setVisibleTree(Search.EFAIA_TREE);
        
        // Set the Search searchSolver.
        this.setSolver(searchSolver);
        
        // Ask the solver for the best action
        Action selectedAction = null;
        try {
        	
            selectedAction = this.getSolver().solve(new Object[]{this.getProblem()});
          
        } catch (Exception ex) {
        	
            Logger.getLogger(AgentDrone.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the selected action
        return selectedAction;

    }

    /**
     * This method is executed by the simulator to give the agent a perception.
     * Then it updates its state.
     * @param p
     */
    @Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
       

    }
}
