import React from 'react';
import { FiUser, FiDollarSign } from 'react-icons/fi';

const Header = () => {
    return (
        <header className="bg-[#141414] text-white shadow-md w-full">
            <nav className="max-w-screen-xl mx-auto flex items-center justify-between p-4 w-full">
                <a href="/" className="flex items-center space-x-3">
                    <span className="text-2xl font-bold">Agiota</span>
                </a>
                <div className="flex space-x-4 md:space-x-8">
                    <a 
                        href="/emprestimos" 
                        className="flex items-center space-x-2 text-white hover:text-blue-400 transition-colors duration-300"
                        aria-current="page"
                    >
                        <FiDollarSign className="text-lg" />
                        <span>Empréstimos</span>
                    </a>
                    <a 
                        href="#" 
                        className="flex items-center space-x-2 text-white hover:text-blue-400 transition-colors duration-300"
                    >
                        <FiUser className="text-lg" />
                        <span>Perfil</span>
                    </a>
                </div>
            </nav>
        </header>
    );
};

export default Header;