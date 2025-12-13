package me.fivekfubi.api;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Stub interface for FiveK Protection plugin API.
 * This interface mirrors the actual plugin's API for compilation purposes.
 * The actual implementation is provided by the FiveK Protection plugin at runtime.
 */
public interface I_DATA_Protection {
    String get_id();
    void set_id(String id);

    long get_current_tick();
    void set_current_tick(long tick);

    long get_damaged_timer();
    void set_damaged_timer(long timer);

    String get_world();
    void set_world(String world);

    int get_chunk_x();
    void set_chunk_x(int x);

    int get_chunk_z();
    void set_chunk_z(int z);

    int get_location_x();
    void set_location_x(int x);

    int get_location_y();
    void set_location_y(int y);

    int get_location_z();
    void set_location_z(int z);

    String get_owner_name();
    void set_owner_name(String name);

    String get_owner_uuid();
    void set_owner_uuid(String uuid);

    String get_protection_name();
    void set_protection_name(String name);

    String get_status();
    void set_status(String status);

    String get_upgrades();
    void set_upgrades(String upgrades);

    String get_storage_items();
    void set_storage_items(String items);

    String get_members();
    void set_members(String members);

    int get_health();
    void set_health(int health);

    int get_fuel();
    void set_fuel(int fuel);

    long get_fuel_timer();
    void set_fuel_timer(long timer);

    int get_generator_stored_money();
    void set_generator_stored_money(int money);

    String get_generator_stored_items();
    void set_generator_stored_items(String items);

    long get_generator_timer_money();
    void set_generator_timer_money(long timer);

    long get_generator_timer_items();
    void set_generator_timer_items(long timer);

    long get_decay_timer();
    void set_decay_timer(long timer);

    String get_member_outsider_id();
    void set_member_outsider_id(String id);

    String get_member_protection_id();
    void set_member_protection_id(String id);

    Map<String, String> get_members_map();
    void remove_member_map(String member);
    String get_member(String member);
    void update_members_database();

    Location get_location();
    void set_location(Location location);

    // Configuration methods
    String get_c_file_path();
    void set_c_file_path(String path);

    String get_c_default_status();
    void set_c_default_status(String status);

    List<String> get_c_world_whitelist();
    void set_c_world_whitelist(List<String> whitelist);

    String get_c_block_material();
    void set_c_block_material(String material);

    double get_c_particle_display_range();
    void set_c_particle_display_range(double range);

    long get_c_particle_display_interval();
    void set_c_particle_display_interval(long interval);

    String get_c_particle_member_file_path();
    void set_c_particle_member_file_path(String path);

    String get_c_particle_outsider_file_path();
    void set_c_particle_outsider_file_path(String path);

    String get_c_rank_default_owner();
    void set_c_rank_default_owner(String rank);

    String get_c_rank_default_member();
    void set_c_rank_default_member(String rank);

    String get_c_rank_default_outsider();
    void set_c_rank_default_outsider(String rank);

    String get_c_rank_default_protection();
    void set_c_rank_default_protection(String rank);

    List<String> get_c_rank_available();
    void set_c_rank_available(List<String> ranks);

    int get_c_health_amount();
    void set_c_health_amount(int amount);

    int get_c_health_capacity();
    void set_c_health_capacity(int capacity);

    int get_c_regen_amount();
    void set_c_regen_amount(int amount);

    long get_c_regen_interval();
    void set_c_regen_interval(long interval);

    long get_c_regen_damage_delay();
    void set_c_regen_damage_delay(long delay);

    int get_c_range_amount();
    void set_c_range_amount(int amount);

    boolean get_c_range_infinite_y();
    void set_c_range_infinite_y(boolean infiniteY);

    double get_c_generator_money_amount();
    void set_c_generator_money_amount(int amount);

    long get_c_generator_money_interval();
    void set_c_generator_money_interval(long interval);

    long get_c_generator_item_interval();
    void set_c_generator_item_interval(long interval);

    double get_c_generator_money_capacity();
    void set_c_generator_money_capacity(int capacity);

    int get_c_generator_slot_capacity();
    void set_c_generator_slot_capacity(int capacity);

    int get_c_storage_slot_capacity();
    void set_c_storage_slot_capacity(int capacity);

    int get_c_fuel_amount();
    void set_c_fuel_amount(int amount);

    int get_c_fuel_capacity();
    void set_c_fuel_capacity(int capacity);

    long get_c_fuel_consume_interval();
    void set_c_fuel_consume_interval(long interval);

    long get_c_decay_interval();
    void set_c_decay_interval(long interval);

    int get_c_decay_redeem_fuel_modify();
    void set_c_decay_redeem_fuel_modify(int modify);

    double get_c_decay_redeem_fuel_multiply();
    void set_c_decay_redeem_fuel_multiply(double multiply);

    int get_c_decay_redeem_health_modify();
    void set_c_decay_redeem_health_modify(int modify);

    double get_c_decay_redeem_health_multiply();
    void set_c_decay_redeem_health_multiply(double multiply);

    int get_c_decay_redeem_generator_money_modify();
    void set_c_decay_redeem_generator_money_modify(int modify);

    double get_c_decay_redeem_generator_money_multiply();
    void set_c_decay_redeem_generator_money_multiply(double multiply);

    int get_c_decay_redeem_generator_items_modify();
    void set_c_decay_redeem_generator_items_modify(int modify);

    double get_c_decay_redeem_generator_items_multiply();
    void set_c_decay_redeem_generator_items_multiply(double multiply);

    double get_c_decay_redeem_storage_remove_items_amount();
    void set_c_decay_redeem_storage_remove_items_amount(double amount);

    boolean get_c_decay_redeem_decay_timer_reset();
    void set_c_decay_redeem_decay_timer_reset(boolean reset);

    int get_c_trap_radius();
    void set_c_trap_radius(int radius);

    int get_c_trap_player_cooldown();
    void set_c_trap_player_cooldown(int cooldown);

    String get_c_trap_events_on_entry_executables();
    void set_c_trap_events_on_entry_executables(String executables);

    int get_c_damage_blast();
    void set_c_damage_blast(int damage);

    int get_c_damage_tool();
    void set_c_damage_tool(int damage);

    int get_c_damage_none();
    void set_c_damage_none(int damage);

    int get_c_defense_amount();
    void set_c_defense_amount(int amount);

    int get_c_defense_capacity();
    void set_c_defense_capacity(int capacity);

    int get_c_defense_scaling_factor();
    void set_c_defense_scaling_factor(int factor);

    double get_c_crops_growth_speed_multiplier();
    void set_c_crops_growth_speed_multiplier(double multiplier);

    double get_c_crops_yield_bonus_chance();
    void set_c_crops_yield_bonus_chance(double chance);

    double get_c_crops_yield_bonus_multiplier();
    void set_c_crops_yield_bonus_multiplier(double multiplier);

    double get_c_crops_multi_harvest_chance();
    void set_c_crops_multi_harvest_chance(double chance);

    double get_c_crops_multi_harvest_multiplier();
    void set_c_crops_multi_harvest_multiplier(double multiplier);

    double get_c_crops_fertilizer_efficiency_chance();
    void set_c_crops_fertilizer_efficiency_chance(double chance);

    double get_c_crops_fertilizer_efficiency_multiplier();
    void set_c_crops_fertilizer_efficiency_multiplier(double multiplier);

    double get_c_crops_exp_chance();
    void set_c_crops_exp_chance(double chance);

    int get_c_crops_exp_amount();
    void set_c_crops_exp_amount(int amount);

    double get_c_mobs_looting_multiplier();
    void set_c_mobs_looting_multiplier(double multiplier);

    double get_c_mobs_exp_multiplier();
    void set_c_mobs_exp_multiplier(double multiplier);

    int get_c_spawner_limit_bonus();
    void set_c_spawner_limit_bonus(int bonus);

    int get_c_spawner_interval_reduction_amount();
    void set_c_spawner_interval_reduction_amount(int amount);

    // Protection calculated values
    int get_protection_health();
    int get_protection_health_capacity();
    int get_protection_regen_amount();
    long get_protection_regen_interval();
    long get_protection_regen_damage_delay();
    int get_protection_range_amount();
    int get_protection_maximum_range_amount();
    int get_protection_generator_money_amount();
    int get_protection_generator_money_capacity();
    int get_protection_generator_slot_capacity();
    int get_protection_storage_slot_capacity();
    int get_protection_fuel();
    long get_protection_fuel_timer_total();
    int get_protection_fuel_capacity();
    long get_protection_generator_money_interval();
    long get_protection_generator_items_interval();
    long get_protection_fuel_consume_interval();
    long get_protection_decay_interval();
    int get_protection_damage_blast();
    int get_protection_damage_tool();
    int get_protection_damage_none();
    int get_protection_defense_amount();
    int get_protection_defense_capacity();
    int get_protection_defense_scaling_factor();

    // Storage methods
    Map<String, Map<String, Map<String, Map<Integer, ItemStack>>>> get_storage_items_map();
    void set_storage_items_map(Map<String, Map<String, Map<String, Map<Integer, ItemStack>>>> map);
    void load();
    void update_storage_database();
    void storage_add_items(String p1, String p2, String p3, Map<Integer, ItemStack> items);
    void storage_remove_item(String p1, String p2, String p3, int slot);
    Map<Integer, ItemStack> get_storage_items_deserialized(String key, int page);

    // Upgrades methods
    Map<String, Integer> get_upgrades_map();
    void add_upgrades_map(String upgrade, int level);
    void remove_upgrades_map(String upgrade, int level);
    Integer get_upgrade_map_amount(String upgrade);
    void update_upgrades_database();

    // Generator stored items methods
    Map<String, Integer> get_generator_stored_items_map();
    void clear_generator_stored_items_map();
    void add_generator_stored_items_map(String item, int amount);
    void remove_generator_stored_items_map(String item, int amount);
    Integer get_generator_stored_items_map_amount(String item);
    Integer get_generator_stored_items_map_amount_total();
    void update_generator_stored_items_database();

    // Serialization methods
    String serialize_storage_map();
    Map<String, Map<String, Map<String, Map<Integer, ItemStack>>>> deserialize_storage_map(String data);
    String serialize_members_map();
    Map<String, String> deserialize_members_map(String data);
    String serialize_upgrades_map();
    Map<String, Integer> deserialize_upgrades_map(String data);
    String serialize_generator_stored_items_map();
    Map<String, Integer> deserialize_generator_stored_items_map(String data);
}
