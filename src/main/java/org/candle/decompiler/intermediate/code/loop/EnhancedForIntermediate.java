package org.candle.decompiler.intermediate.code.loop;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.bcel.classfile.Utility;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.commons.lang.StringUtils;
import org.candle.decompiler.intermediate.code.ConditionalIntermediate;
import org.candle.decompiler.intermediate.expression.Expression;
import org.candle.decompiler.intermediate.expression.Variable;
import org.candle.decompiler.intermediate.visitor.IntermediateVisitor;

public class EnhancedForIntermediate extends WhileIntermediate {

	private final Variable variable;
	private final Expression right;
	
	public EnhancedForIntermediate(InstructionHandle ih, ConditionalIntermediate ci, Variable variable, Expression right) {
		super(ih, ci);

		this.variable = variable;
		this.right = right;
	}
	public EnhancedForIntermediate(WhileIntermediate whileIntermediate, Variable variable, Expression right) {
		this(whileIntermediate.getInstruction(), whileIntermediate.getConditionalIntermediate(), variable, right);
	}
	

	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		try {
			String outputType = Utility.signatureToString(variable.getType().getSignature());
			
			if(StringUtils.contains(outputType, ".")) {
				outputType = StringUtils.substringAfterLast(outputType, ".");
			}
			sw.append(outputType);
			sw.append(" ");
			sw.append(variable.getName());
			
			sw.append(" : ");
			right.write(sw);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "For: "+sw.toString();
	}
	
	
	@Override
	public void accept(IntermediateVisitor visitor) {
		visitor.visitAbstractLine(this);
		visitor.visitEnhancedForLoopLine(this);
	}
	
	
}
