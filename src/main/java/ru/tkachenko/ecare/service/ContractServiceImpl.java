package ru.tkachenko.ecare.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tkachenko.ecare.dao.ContractDAO;
import ru.tkachenko.ecare.dto.ClientDTO;
import ru.tkachenko.ecare.dto.ContractDTO;
import ru.tkachenko.ecare.models.Contract;

import java.util.List;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractDAO contractDAO;
    private final ModelMapper modelMapper;
    private final ModelMapper mapper;

    @Autowired
    public ContractServiceImpl(ContractDAO contractDAO, ModelMapper modelMapper, ModelMapper mapper) {
        this.contractDAO = contractDAO;
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    Contract contract = new Contract();

    @Override
    @Transactional(readOnly = true)
    public List<ContractDTO> showAll() {
        return modelMapper.map(contractDAO.showAll(), new TypeToken<List<ContractDTO>>() {}.getType());
    }

    @Override
    @Transactional(readOnly = true)
    public ContractDTO showById(int id) {
        Contract contract = contractDAO.showById(id);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);
        contractDTO.setClientDTO(mapper.map(contract.getClient(), ClientDTO.class));
        return contractDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO showClientByNumber(int number) {
        Contract contract = (Contract) contractDAO.showClientByNumber(number);
        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);
        contractDTO.setClientDTO(mapper.map(contract.getClient(), ClientDTO.class));
        ModelMapper mp = new ModelMapper();
        Set<ContractDTO> contractDTOSet = mp.map(contract.getClient().getContractSet(), new TypeToken<Set<ContractDTO>>() {}.getType());
        contractDTO.getClientDTO().setContractSetDTO(contractDTOSet);
        return contractDTO.getClientDTO();
    }

    @Override
    @Transactional
    public void save(ContractDTO contractDTO) {
        contract = toEntity(contractDTO);
        contractDAO.save(contract);
    }

    @Override
    @Transactional
    public void update(ContractDTO contractDTO) {
        contract = toEntity(contractDTO);
        contractDAO.update(contract);
    }

    @Override
    @Transactional
    public void delete(ContractDTO contractDTO, int id) {
        contract = toEntity(contractDTO);
        contractDAO.delete(contract, id);
    }

    @Override
    public Contract toEntity(ContractDTO contractDTO) {
        return modelMapper.map(contractDTO, Contract.class);
    }
}
