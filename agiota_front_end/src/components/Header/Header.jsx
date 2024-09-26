import React, { useState } from 'react';
import { FiUser } from 'react-icons/fi';
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate();
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);

    const handleLogout = () => {
        localStorage.removeItem('@Auth:user'); 
        localStorage.removeItem('isAuthenticated');
        window.location.reload();
    };

    const toggleDropdown = () => {
        setIsDropdownOpen(!isDropdownOpen);
    };

    const user = JSON.parse(localStorage.getItem('@Auth:user'));
    const nome = user?.usuario?.nome || 'Usuário Desconhecido';
    const email = user?.usuario?.email || 'email@desconhecido.com';

    return (
        <header className="bg-black text-white shadow-lg w-full relative">
            <nav className="max-w-screen-xl mx-auto flex items-center justify-between p-4">
                <a href="/app" className="flex items-center space-x-3">
                    <span className="text-2xl font-bold hover:text-gray-400 transition-colors duration-300">A.G.I.O.T.A.</span>
                </a>
                <div className="flex space-x-4 md:space-x-8 relative">
                    <a
                        href="/app/emprestimos"
                        className="flex items-center space-x-2 text-white hover:text-gray-400 transition-colors duration-300"
                    >
                        <span>Emprestimos</span>
                    </a>
                    {user?.usuario?.tipo === "Cliente" && (
                        <>
                            <a
                                href="/app/agiotas"
                                className="flex items-center space-x-2 text-white hover:text-blue-400 transition-colors duration-300"
                                aria-current="page"
                            >
                                <span>Agiotas</span>
                            </a>
                        </>
                    )}
                    <button
                        onClick={toggleDropdown}
                        className="flex items-center text-white hover:text-gray-400 font-medium text-sm px-5 py-2.5"
                    >
                        <FiUser className="text-lg" />
                        <span className="ml-2">Perfil</span>
                    </button>

                    <div
                        className={`${isDropdownOpen ? 'block' : 'hidden'} absolute right-0 mt-2 z-50 bg-gray-800 divide-y divide-gray-600 rounded-lg shadow-md`}
                        style={{ top: '100%' }}
                    >
                        <div className="px-4 py-3 text-sm text-gray-300">
                            <div className="font-bold">{nome}</div>
                            <div className="truncate">{email}</div>
                        </div>
                        <ul className="py-2 text-sm text-gray-200">
                            <li>
                                <a
                                    href="#"
                                    className="block px-4 py-2 hover:bg-gray-700"
                                >
                                    Lembretes
                                </a>
                            </li>
                        </ul>
                        <div className="py-2">
                            <a
                                href="#"
                                className="block px-4 py-2 text-sm text-gray-200 hover:bg-gray-700"
                                onClick={handleLogout}
                            >
                                Sair
                            </a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
    );
};

export default Header;
