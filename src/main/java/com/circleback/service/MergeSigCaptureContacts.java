package com.circleback.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.circleback.response.SigCaptureContact;
import com.circleback.response.SigCapturePhoneNumber;
import com.circleback.response.SigCapturePostalAddress;
import com.circleback.response.SigCaptureSocialProfile;

public class MergeSigCaptureContacts {
	public static List<SigCaptureContact> mergeSigCaptureContactList(List<SigCaptureContact> listOfSigCaptureContact,
			List<SigCaptureContact> listOfSigCaptureContactToMergeWith) {
		Set<SigCaptureContact> setOfSigCaptureContactFinal = new HashSet<SigCaptureContact>();
		for (SigCaptureContact sigCaptureContactToMergeWith : listOfSigCaptureContactToMergeWith) {
			for (SigCaptureContact sigCaptureContact : listOfSigCaptureContact) {
				if (sigCaptureContact.equals(sigCaptureContactToMergeWith)) {
					setOfSigCaptureContactFinal
							.add(getMergedSigCaptureContact(sigCaptureContact, sigCaptureContactToMergeWith));
				}
			}
		}

		setOfSigCaptureContactFinal.addAll(listOfSigCaptureContact);
		setOfSigCaptureContactFinal.addAll(listOfSigCaptureContactToMergeWith);
		return new ArrayList<SigCaptureContact>(setOfSigCaptureContactFinal);
	}

	private static SigCaptureContact getMergedSigCaptureContact(SigCaptureContact sigCaptureContact,
			SigCaptureContact sigCaptureContactToMergeWith) {
		SigCaptureContact sigCaptureContactFinal = new SigCaptureContact();

		if (!StringUtils.isEmpty(sigCaptureContact.getFull_name())) {
			sigCaptureContactFinal.setFull_name(sigCaptureContact.getFull_name());
		} else {
			sigCaptureContactFinal.setFull_name(sigCaptureContactToMergeWith.getFull_name());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getPrefix())) {
			sigCaptureContactFinal.setPrefix(sigCaptureContact.getPrefix());
		} else {
			sigCaptureContactFinal.setPrefix(sigCaptureContactToMergeWith.getPrefix());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getFirst_name())) {
			sigCaptureContactFinal.setFirst_name(sigCaptureContact.getFirst_name());
		} else {
			sigCaptureContactFinal.setFirst_name(sigCaptureContactToMergeWith.getFirst_name());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getMiddle_name())) {
			sigCaptureContactFinal.setMiddle_name(sigCaptureContact.getMiddle_name());
		} else {
			sigCaptureContactFinal.setMiddle_name(sigCaptureContactToMergeWith.getMiddle_name());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getLast_name())) {
			sigCaptureContactFinal.setLast_name(sigCaptureContact.getLast_name());
		} else {
			sigCaptureContactFinal.setLast_name(sigCaptureContactToMergeWith.getLast_name());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getSuffix())) {
			sigCaptureContactFinal.setSuffix(sigCaptureContact.getSuffix());
		} else {
			sigCaptureContactFinal.setSuffix(sigCaptureContactToMergeWith.getSuffix());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getNick_name())) {
			sigCaptureContactFinal.setNick_name(sigCaptureContact.getNick_name());
		} else {
			sigCaptureContactFinal.setNick_name(sigCaptureContactToMergeWith.getNick_name());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getTitle())) {
			sigCaptureContactFinal.setTitle(sigCaptureContact.getTitle());
		} else {
			sigCaptureContactFinal.setTitle(sigCaptureContactToMergeWith.getTitle());
		}

		if (!StringUtils.isEmpty(sigCaptureContact.getCompany())) {
			sigCaptureContactFinal.setCompany(sigCaptureContact.getCompany());
		} else {
			sigCaptureContactFinal.setCompany(sigCaptureContactToMergeWith.getCompany());
		}

		sigCaptureContactFinal.setEmails(
				mergeStringArraysWithUnique(sigCaptureContact.getEmails(), sigCaptureContactToMergeWith.getEmails()));
		sigCaptureContactFinal.setUrls(
				mergeStringArraysWithUnique(sigCaptureContact.getUrls(), sigCaptureContactToMergeWith.getUrls()));
		sigCaptureContactFinal
				.setAddresses(mergeSigCapturePostalAddress(sigCaptureContact, sigCaptureContactToMergeWith));
		sigCaptureContactFinal
				.setPhone_numbers(mergeSigCapturePhoneNumber(sigCaptureContact, sigCaptureContactToMergeWith));
		sigCaptureContactFinal
				.setSocial_profiles(mergeSigCaptureSocialProfile(sigCaptureContact, sigCaptureContactToMergeWith));

		return sigCaptureContactFinal;
	}

	private static SigCapturePostalAddress[] mergeSigCapturePostalAddress(SigCaptureContact sigCaptureContact,
			SigCaptureContact sigCaptureContactToMergeWith) {
		if (sigCaptureContact.getAddresses() == null && sigCaptureContactToMergeWith.getAddresses() != null) {
			return sigCaptureContactToMergeWith.getAddresses();
		} else if (sigCaptureContact.getAddresses() != null && sigCaptureContactToMergeWith.getAddresses() == null) {
			return sigCaptureContact.getAddresses();
		} else if (sigCaptureContact.getAddresses() == null && sigCaptureContactToMergeWith.getAddresses() == null) {
			return null;
		}

		Set<SigCapturePostalAddress> sigCapturePostalAddressSetFinal = new HashSet<SigCapturePostalAddress>();
		List<SigCapturePostalAddress> sigCapturePostalAddressList = Arrays.asList(sigCaptureContact.getAddresses());
		List<SigCapturePostalAddress> sigCapturePostalAddressListOther = Arrays
				.asList(sigCaptureContactToMergeWith.getAddresses());

		sigCapturePostalAddressSetFinal.addAll(sigCapturePostalAddressList);
		sigCapturePostalAddressSetFinal.addAll(sigCapturePostalAddressListOther);
		return sigCapturePostalAddressSetFinal.toArray(new SigCapturePostalAddress[0]);
	}

	private static SigCapturePhoneNumber[] mergeSigCapturePhoneNumber(SigCaptureContact sigCaptureContact,
			SigCaptureContact sigCaptureContactToMergeWith) {
		if (sigCaptureContact.getPhone_numbers() == null && sigCaptureContactToMergeWith.getPhone_numbers() != null) {
			return sigCaptureContactToMergeWith.getPhone_numbers();
		} else if (sigCaptureContact.getPhone_numbers() != null
				&& sigCaptureContactToMergeWith.getPhone_numbers() == null) {
			return sigCaptureContact.getPhone_numbers();
		} else if (sigCaptureContact.getPhone_numbers() == null
				&& sigCaptureContactToMergeWith.getPhone_numbers() == null) {
			return null;
		}

		Set<SigCapturePhoneNumber> sigCapturePhoneNumberSetFinal = new HashSet<SigCapturePhoneNumber>();
		List<SigCapturePhoneNumber> sigCapturePhoneNumberList = Arrays.asList(sigCaptureContact.getPhone_numbers());
		List<SigCapturePhoneNumber> sigCapturePhoneNumberListOther = Arrays
				.asList(sigCaptureContactToMergeWith.getPhone_numbers());
		sigCapturePhoneNumberSetFinal.addAll(sigCapturePhoneNumberList);
		sigCapturePhoneNumberSetFinal.addAll(sigCapturePhoneNumberListOther);
		return sigCapturePhoneNumberSetFinal.toArray(new SigCapturePhoneNumber[0]);
	}

	private static SigCaptureSocialProfile[] mergeSigCaptureSocialProfile(SigCaptureContact sigCaptureContact,
			SigCaptureContact sigCaptureContactToMergeWith) {
		if (sigCaptureContact.getSocial_profiles() == null
				&& sigCaptureContactToMergeWith.getSocial_profiles() != null) {
			return sigCaptureContactToMergeWith.getSocial_profiles();
		} else if (sigCaptureContact.getSocial_profiles() != null
				&& sigCaptureContactToMergeWith.getSocial_profiles() == null) {
			return sigCaptureContact.getSocial_profiles();
		} else if (sigCaptureContact.getSocial_profiles() == null
				&& sigCaptureContactToMergeWith.getSocial_profiles() == null) {
			return null;
		}

		Set<SigCaptureSocialProfile> sigCaptureSocialProfileSetFinal = new HashSet<SigCaptureSocialProfile>();
		List<SigCaptureSocialProfile> sigCaptureSocialProfileList = Arrays
				.asList(sigCaptureContact.getSocial_profiles());
		List<SigCaptureSocialProfile> sigCaptureSocialProfileListOther = Arrays
				.asList(sigCaptureContactToMergeWith.getSocial_profiles());
		sigCaptureSocialProfileSetFinal.addAll(sigCaptureSocialProfileList);
		sigCaptureSocialProfileSetFinal.addAll(sigCaptureSocialProfileListOther);
		return sigCaptureSocialProfileSetFinal.toArray(new SigCaptureSocialProfile[0]);
	}

	private static String[] mergeStringArraysWithUnique(String[] a, String[] b) {
		if (a == null && b != null) {
			return b;
		} else if (a != null && b == null) {
			return a;
		} else if (a == null && b == null) {
			return null;
		}

		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(a));
		set.addAll(Arrays.asList(b));
		String[] stringArray = set.toArray(new String[0]);
		return stringArray;
	}
}
