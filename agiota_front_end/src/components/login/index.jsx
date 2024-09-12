import kc from "../../keycloack/kc";

const Login = ({ goToRegister }) => {

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-[#0f0f0f]">
            {/* Header com dois botões no topo */}
            <header className="flex justify-end w-full px-4 py-4 bg-[#141414] shadow-lg">
                <div className="flex space-x-4">
                    <button
                        onClick={() => console.log(kc.login())}
                        className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg"
                    >
                        Login
                    </button>
                    <button
                        onClick={() => kc.logout({ redirectUri: 'http://localhost:3000/' })}
                        className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-lg"
                    >
                        Logout
                    </button>
                    <button
                        onClick={goToRegister}
                        className="bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-4 rounded-lg"
                    >
                        Cadastre-se
                    </button>
                </div>
            </header>

            {/* Nome A.G.I.O.T.A centralizado */}
            <div className="flex items-center justify-center flex-1">
                <h2 className="text-center text-5xl font-semibold text-white">
                    A.G.I.O.T.A
                </h2>
            </div>
        </div>
    );
};

export default Login;
