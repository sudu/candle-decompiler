package org.candle.decompiler.intermediate.expression;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.bcel.generic.InstructionHandle;

public class ArrayPositionReference extends Expression {

	private final Expression arrayReference;
	private final Expression arrayPosition;
	
	public ArrayPositionReference(InstructionHandle instructionHandle, Expression arrayReference, Expression arrayPosition) {
		super(instructionHandle);
		this.arrayReference = arrayReference;
		this.arrayPosition = arrayPosition;
	}
	
	public Expression getArrayReference() {
		return arrayReference;
	}
	
	public Expression getArrayPosition() {
		return arrayPosition;
	}

	@Override
	public void write(Writer builder) throws IOException {
		arrayReference.write(builder);
		builder.append("[");
		arrayPosition.write(builder);
		builder.append("]");
	}
	
	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		try {
			write(sw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();
	}
	
}
