package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.DefaultProcessing;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessType;

public class DefaultProcessingTest {

	/**
	 * Try to change default processing to a list of one process type.  
	 */
	@Test
	public void testDefaultProcessingAdd() {
		DefaultProcessing defaultProcess = new DefaultProcessing();
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 0);
		ProcessType data = new ProcessType("name", "path");
		ArrayList<ProcessType> list = new ArrayList<ProcessType>();
		list.add(data);
		defaultProcess.updateCurrentlySelected(list);
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 1);
		assertTrue(defaultProcess.getCurrentlySelectedProcesses().contains(data));
	}
	
	/**
	 * Try to change default processing to an empty list of process types.  
	 */
	@Test
	public void testEmptyDefaultProcessing() {
		DefaultProcessing defaultProcess = new DefaultProcessing();
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 0);
		ArrayList<ProcessType> list = new ArrayList<ProcessType>();
		defaultProcess.updateCurrentlySelected(list);
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 0);
	}
	
	/**
	 * Try to change default processing to null.  Default Processing should be empty.
	 */
	@Test
	public void testNullDefaultProcessing() {
		DefaultProcessing defaultProcess = new DefaultProcessing();
		defaultProcess.updateCurrentlySelected(null);
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 0);
	}

	/**
	 * Change default processing from one process to no processes
	 */
	/**
	 * Try to change default processing to a list of one process type.  
	 */
	@Test
	public void testDefaultProcessingRemove() {
		DefaultProcessing defaultProcess = new DefaultProcessing();
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 0);
		ProcessType data = new ProcessType("name", "path");
		ArrayList<ProcessType> list = new ArrayList<ProcessType>();
		list.add(data);
		defaultProcess.updateCurrentlySelected(list);
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 1);
		assertTrue(defaultProcess.getCurrentlySelectedProcesses().contains(data));
		list.clear();
		defaultProcess.updateCurrentlySelected(list);
		assertEquals(defaultProcess.getCurrentlySelectedProcesses().size(), 0);
		
	}
}
