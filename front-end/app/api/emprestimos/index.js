import {getAccessToken} from "@/utils/sessionTokenAccessor";

const apiUrl = 'http://localhost:8080'

export const emprestimosCliente = async () => {
    const response = await fetch (apiUrl+'/cliente/1/emprestimos',
        { cache: 'no-store' ,
            headers: {
                "Content-Type": "application/json"
            },
        });

    return await response.json();
}