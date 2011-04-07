
package abstractions.side;


enum Side implements SideInterface {

    FIRST_SIDE() {

        @Override
        public final Side getNextSide() {
            return SECOND_SIDE;
        }

        @Override
        public final Side getNegation() {
            return NOT_FIRST_SIDE;
        }
    },

    SECOND_SIDE() {

        @Override
        public final Side getNextSide() {
            return FIRST_SIDE;
        }

        @Override
        public final Side getNegation() {
            return NOT_SECOND_SIDE;
        }
    },

    NULL_SIDE {

        @Override
        public final Side getNextSide() {
            return this;
        }

        @Override
        public final Side getNegation() {
            return this;
        }
    },

    NOT_FIRST_SIDE() {

        @Override
        public final Side getNextSide() {
            return this;
        }

        @Override
        public final Side getNegation() {
            return FIRST_SIDE;
        }
    },

    NOT_SECOND_SIDE {

        @Override
        public final Side getNextSide() {
            return this;
        }

        @Override
        public final Side getNegation() {
            return SECOND_SIDE;
        }
    };
    
    public final boolean isFirstSide() {
        return this.equals(FIRST_SIDE);
    }
    
    public final boolean isSecondSide() {
        return this.equals(SECOND_SIDE);
    }        
        
    public final boolean isOneSide() {
        return this.isFirstSide() || this.isSecondSide();
    }
    
    public final boolean isNull() {
        return this.equals(NULL_SIDE);
    }
    
    public abstract Side getNextSide();
    
    public abstract Side getNegation();    

}