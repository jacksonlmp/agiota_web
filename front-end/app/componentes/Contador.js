'use client';

import { useState } from "react";

export default function Contador () {
    const[contador, setContador] = useState(0);

    return ( <button 
                className="btn btn-primary"
                onClick={() => setContador(contador + 1)}
             >
                Contador: {contador}
            </button>
    );
}