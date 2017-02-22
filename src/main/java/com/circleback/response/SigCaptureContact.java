package com.circleback.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SigCaptureContact {
	private String full_name;
	private String prefix;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String suffix;
	private String nick_name;
	private String title;
	private String company;
	private String[] emails;
	private SigCapturePostalAddress[] addresses;
	private SigCapturePhoneNumber[] phone_numbers;
	private SigCaptureSocialProfile[] social_profiles;
	private String[] urls;

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

	public SigCapturePostalAddress[] getAddresses() {
		return addresses;
	}

	public void setAddresses(SigCapturePostalAddress[] addresses) {
		this.addresses = addresses;
	}

	public SigCapturePhoneNumber[] getPhone_numbers() {
		return phone_numbers;
	}

	public void setPhone_numbers(SigCapturePhoneNumber[] phone_numbers) {
		this.phone_numbers = phone_numbers;
	}

	public SigCaptureSocialProfile[] getSocial_profiles() {
		return social_profiles;
	}

	public void setSocial_profiles(SigCaptureSocialProfile[] social_profiles) {
		this.social_profiles = social_profiles;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	@Override
	public String toString() {
		return "SigCaptureContact [full_name=" + full_name + ", prefix=" + prefix + ", first_name=" + first_name
				+ ", middle_name=" + middle_name + ", last_name=" + last_name + ", suffix=" + suffix + ", nick_name="
				+ nick_name + ", title=" + title + ", company=" + company + ", emails=" + Arrays.toString(emails)
				+ ", addresses=" + Arrays.toString(addresses) + ", phone_numbers=" + Arrays.toString(phone_numbers)
				+ ", social_profiles=" + Arrays.toString(social_profiles) + ", urls=" + Arrays.toString(urls) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SigCaptureContact other = (SigCaptureContact) obj;
		if (!emailsMatch(emails, other.emails) && !phonesMatch(phone_numbers, other.phone_numbers))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		return true;
	}

	private boolean emailsMatch(String[] emails, String[] emailsOther) {
		if (emails == null || emailsOther == null) {
			return false;
		}

		Set<String> hashSetOfEmails = new HashSet<String>(Arrays.asList(emails));
		hashSetOfEmails.retainAll(Arrays.asList(emailsOther));
		return !hashSetOfEmails.isEmpty();
	}

	private boolean phonesMatch(SigCapturePhoneNumber[] phone_numbers, SigCapturePhoneNumber[] phone_numbersOther) {
		if (phone_numbers == null || phone_numbersOther == null) {
			return false;
		}

		List<SigCapturePhoneNumber> listOfSigCapturePhoneNumber = new ArrayList<SigCapturePhoneNumber>(
				Arrays.asList(phone_numbers));
		List<SigCapturePhoneNumber> listOfSigCapturePhoneNumberToMergeWith = new ArrayList<SigCapturePhoneNumber>(
				Arrays.asList(phone_numbersOther));
		for (SigCapturePhoneNumber sigCapturePhoneNumber : listOfSigCapturePhoneNumber) {
			for (SigCapturePhoneNumber sigCapturePhoneNumberToMergeWith : listOfSigCapturePhoneNumberToMergeWith) {
				if (sigCapturePhoneNumberToMergeWith.getPhone_number()
						.equals(sigCapturePhoneNumber.getPhone_number())) {
					return true;
				}
			}
		}
		return false;
	}
}
