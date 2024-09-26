import React, { useState } from 'react';
import { Modal, Box, Typography, TextField, Button } from '@mui/material';
import axios from 'axios';

const AvaliacaoModal = ({ isOpen, closeModal, idAvaliado }) => {
  const [nota, setNota] = useState('');
  const [descricao, setDescricao] = useState('');
  const user = JSON.parse(localStorage.getItem('@Auth:user'));

  const handleSubmit = async () => {
    try {
      const response = await axios.post('http://localhost:8080/avaliar', {
        nota: parseInt(nota, 10),
        idAvaliado,
        descricao 
      }, {
        headers: {
          'Authorization': `Bearer ${user?.token}`
        }
      });
      console.log('Avaliação enviada com sucesso:', response.data);
      closeModal(); 
    } catch (error) {
      console.error('Erro ao enviar avaliação:', error.response ? error.response.data : error.message);
    }
  };

  const handleNotaChange = (e) => {
    const value = Math.max(1, Math.min(5, Number(e.target.value)));
    setNota(value);
  };

  return (
    <Modal open={isOpen} onClose={closeModal}>
      <Box sx={{ width: 400, padding: 4, margin: 'auto', marginTop: '10%', backgroundColor: 'white', borderRadius: 2 }}>
        <Typography variant="h6" component="h2" sx={{ marginBottom: 2 }}>
          Avaliar Usuário
        </Typography>
        <TextField
          fullWidth
          label="Nota"
          type="number"
          value={nota}
          onChange={handleNotaChange}
          inputProps={{ min: 1, max: 5 }}
          sx={{ marginBottom: 2 }}
        />
        <TextField
          fullWidth
          label="Descrição"
          multiline
          rows={4}
          value={descricao}
          onChange={(e) => setDescricao(e.target.value)}
          sx={{ marginBottom: 2 }}
        />
        <Button variant="contained" color="primary" onClick={handleSubmit}>
          Enviar Avaliação
        </Button>
      </Box>
    </Modal>
  );
};

export default AvaliacaoModal;
