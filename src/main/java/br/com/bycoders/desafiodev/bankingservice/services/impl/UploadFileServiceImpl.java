package br.com.bycoders.desafiodev.bankingservice.services.impl;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.Owner;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.Transactions;
import br.com.bycoders.desafiodev.bankingservice.domains.enums.TypeOperationEnum;
import br.com.bycoders.desafiodev.bankingservice.helpers.ProcessorCNABHelper;
import br.com.bycoders.desafiodev.bankingservice.repositories.OwnerRepository;
import br.com.bycoders.desafiodev.bankingservice.repositories.TransactionRepository;
import br.com.bycoders.desafiodev.bankingservice.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final OwnerRepository ownerRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public UploadFileServiceImpl(OwnerRepository ownerRepository, TransactionRepository transactionRepository) {
        this.ownerRepository = ownerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Map<Owner, List<Transactions>> uploadFile(MultipartFile cnabFile) throws IOException {

        Map<Owner, List<Transactions>> transactions = ProcessorCNABHelper.interpreteFile(cnabFile);

        transactions
                .entrySet()
                .forEach(entry -> {

                    Owner owner = entry.getKey();
                    List<Transactions> transactionsList = entry.getValue();
                    Owner ownerFound = ownerRepository.findByCpf(owner.getCpf());



                    if (Objects.nonNull(ownerFound)) {
                        transactionsList.forEach(UploadFileServiceImpl::setDescriptionAndTypeOperation);
                        transactionRepository.saveAll(transactionsList);
                    } else {
                        Owner ownerSaved = ownerRepository.save(owner);
                        transactionsList.forEach(transactions1 -> {
                            setOwnerAndDescriptionOperation(transactions1, ownerSaved);
                        });
                        transactionRepository.saveAll(transactionsList);
                    }
                });
        return transactions;
    }

    private static void setOwnerAndDescriptionOperation(Transactions transactions1, Owner ownerFound) {
        setDescriptionAndTypeOperation(transactions1);
        transactions1.setOwner(ownerFound);
    }

    private static void setDescriptionAndTypeOperation(Transactions transactions1) {
        TypeOperationEnum operationByTypeOperation = TypeOperationEnum.getOperationByTypeOperation(Integer.parseInt(transactions1.getTypeOperation()));
        transactions1.setDescriptionOperation(operationByTypeOperation.getDescription());
        transactions1.setTypeOperation(String.valueOf(operationByTypeOperation.getTypeOperation()));
        transactions1.setType(operationByTypeOperation.getType());
    }
}
