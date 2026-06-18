package com.example.e_commerce_website.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.e_commerce_website.dto.AddressRequestDTO;
import com.example.e_commerce_website.dto.AddressResponseDTO;
import com.example.e_commerce_website.dto.ApiResponse;
import com.example.e_commerce_website.entity.Address;
import com.example.e_commerce_website.repository.AddressRepository;
import com.example.e_commerce_website.serviceInterface.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    
    
    /* =========================
       ADD ADDRESS
    ========================= */

    @Override
    public ApiResponse addAddress(Long userId, AddressRequestDTO dto) {

        try {

            /* REMOVE OLD DEFAULT */
            if (Boolean.TRUE.equals(dto.getIsDefault())) {

                List<Address> defaultAddresses =
                        addressRepository.findByUserIdAndIsDefault(userId, true);

                for (Address address : defaultAddresses) {
                    address.setIsDefault(false);
                }

                addressRepository.saveAll(defaultAddresses);
            }

            Address address = new Address();

            address.setUserId(userId);

            address.setFullName(dto.getFullName());

            address.setPhone(dto.getPhone());

            address.setAddressLine1(dto.getAddressLine1());

            address.setAddressLine2(dto.getAddressLine2());

            address.setLandmark(dto.getLandmark());

            address.setCity(dto.getCity());

            address.setState(dto.getState());

            address.setCountry(dto.getCountry());

            address.setPincode(dto.getPincode());

            address.setIsDefault(
                    Boolean.TRUE.equals(dto.getIsDefault())
            );

            Address savedAddress =
                    addressRepository.save(address);

            return new ApiResponse(
                    200,
                    "Address Added Successfully",
                    mapToDTO(savedAddress)
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ApiResponse(
                    500,
                    "Failed To Add Address",
                    null
            );
        }
    }

    
    
    /* =========================
       GET ALL ADDRESSES
    ========================= */

    @Override
    public ApiResponse getAllAddresses(Long userId) {

        try {

            List<Address> addresses =
                    addressRepository.findByUserId(userId);

            List<AddressResponseDTO> response =
                    addresses.stream()
                            .map(this::mapToDTO)
                            .collect(Collectors.toList());

            return new ApiResponse(
                    200,
                    "All Addresses Fetched Successfully",
                    response
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ApiResponse(
                    500,
                    "Failed To Fetch Addresses",
                    null
            );
        }
    }

    
    
    /* =========================
       UPDATE ADDRESS
    ========================= */

    @Override
    public ApiResponse updateAddress(Long userId,
                                     Long addressId,
                                     AddressRequestDTO dto) {

        try {

            Optional<Address> optionalAddress =
                    addressRepository.findByIdAndUserId(addressId, userId);

            if (optionalAddress.isEmpty()) {

                return new ApiResponse(
                        400,
                        "Address Not Found",
                        null
                );
            }

            Address address = optionalAddress.get();

            /* REMOVE OLD DEFAULT */
            if (Boolean.TRUE.equals(dto.getIsDefault())) {

                List<Address> defaultAddresses =
                        addressRepository.findByUserIdAndIsDefault(userId, true);

                for (Address add : defaultAddresses) {

                    add.setIsDefault(false);
                }

                addressRepository.saveAll(defaultAddresses);
            }

            address.setFullName(dto.getFullName());

            address.setPhone(dto.getPhone());

            address.setAddressLine1(dto.getAddressLine1());

            address.setAddressLine2(dto.getAddressLine2());

            address.setLandmark(dto.getLandmark());

            address.setCity(dto.getCity());

            address.setState(dto.getState());

            address.setCountry(dto.getCountry());

            address.setPincode(dto.getPincode());

            address.setIsDefault(
                    Boolean.TRUE.equals(dto.getIsDefault())
            );

            Address updatedAddress =
                    addressRepository.save(address);

            return new ApiResponse(
                    200,
                    "Address Updated Successfully",
                    mapToDTO(updatedAddress)
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ApiResponse(
                    500,
                    "Failed To Update Address",
                    null
            );
        }
    }

    
    
    /* =========================
       DELETE ADDRESS
    ========================= */

    @Override
    public ApiResponse deleteAddress(Long userId,
                                     Long addressId) {

        try {

            Optional<Address> optionalAddress =
                    addressRepository.findByIdAndUserId(addressId, userId);

            if (optionalAddress.isEmpty()) {

                return new ApiResponse(
                        400,
                        "Address Not Found",
                        null
                );
            }

            addressRepository.delete(optionalAddress.get());

            return new ApiResponse(
                    200,
                    "Address Deleted Successfully",
                    null
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ApiResponse(
                    500,
                    "Failed To Delete Address",
                    null
            );
        }
    }

    
    
    /* =========================
       SET DEFAULT ADDRESS
    ========================= */

    @Override
    public ApiResponse setDefaultAddress(Long userId,
                                         Long addressId) {

        try {

            Optional<Address> optionalAddress =
                    addressRepository.findByIdAndUserId(addressId, userId);

            if (optionalAddress.isEmpty()) {

                return new ApiResponse(
                        400,
                        "Address Not Found",
                        null
                );
            }

            /* REMOVE OLD DEFAULT */

            List<Address> defaultAddresses =
                    addressRepository.findByUserIdAndIsDefault(userId, true);

            for (Address address : defaultAddresses) {

                address.setIsDefault(false);
            }

            addressRepository.saveAll(defaultAddresses);

            /* SET NEW DEFAULT */

            Address selectedAddress =
                    optionalAddress.get();

            selectedAddress.setIsDefault(true);

            Address updatedAddress =
                    addressRepository.save(selectedAddress);

            return new ApiResponse(
                    200,
                    "Default Address Updated Successfully",
                    mapToDTO(updatedAddress)
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new ApiResponse(
                    500,
                    "Failed To Update Default Address",
                    null
            );
        }
    }

    
    
    /* =========================
       ENTITY TO DTO
    ========================= */

    private AddressResponseDTO mapToDTO(Address address) {

        AddressResponseDTO dto =
                new AddressResponseDTO();

        dto.setId(address.getId());

        dto.setFullName(address.getFullName());

        dto.setPhone(address.getPhone());

        dto.setAddressLine1(address.getAddressLine1());

        dto.setAddressLine2(address.getAddressLine2());

        dto.setLandmark(address.getLandmark());

        dto.setCity(address.getCity());

        dto.setState(address.getState());

        dto.setCountry(address.getCountry());

        dto.setPincode(address.getPincode());

        dto.setIsDefault(address.getIsDefault());

        return dto;
    }
}