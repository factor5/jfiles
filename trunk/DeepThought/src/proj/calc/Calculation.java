package proj.calc;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Calculation {
    
    ADD {
	BigDecimal apply(BigDecimal x, BigDecimal y) {
	    return x.add(y, mc);
	}
    },
    
    SUB {
	BigDecimal apply(BigDecimal x, BigDecimal y) {
	    return x.subtract(y, mc);
	}
    },
    
    MUL {
	BigDecimal apply(BigDecimal x, BigDecimal y) {
	    return x.multiply(y, mc);
	}
    },
    
    DIV {
	BigDecimal apply(BigDecimal x, BigDecimal y) {
	    return x.divide(y, mc);
	}
    };
    
    MathContext mc = MathContext.DECIMAL128;
    
    abstract BigDecimal apply(BigDecimal x, BigDecimal y);
    
}
